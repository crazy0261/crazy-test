package com.example.crazytest.imp;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseResult;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import com.example.crazytest.entity.req.ProcessCaseReq;
import com.example.crazytest.enums.PriorityEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ProcessCaseVO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 13:40
 * @DESRIPTION
 */

@Service
public class ProcessCaseServiceImp implements ProcessCaseService {

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  ProcessCaseResultRepositoryService processCaseResultRepositoryService;

  @Autowired
  UserRepositoryService userRepository;

  @Override
  public IPage<ProcessCaseVO> listPage(ProcessCaseDTO processCaseDTO) {
    AssertUtil.assertTrue(Objects.isNull(processCaseDTO.getTreeKey()),
        ResultEnum.TREE_NODE_NOT_KEY_FAIL.getMessage());

    List<Long> ids = Optional.ofNullable(processCaseDTO.getRecentExecResult())
        .map(result -> processCaseResultRepositoryService
            .list(BaseContext.getSelectProjectId(), result).stream()
            .map(ProcessCaseResult::getCaseId).collect(Collectors.toList()))
        .orElse(null);

    if (StringUtils.isNotBlank(processCaseDTO.getRecentExecResult()) && CollUtil.isEmpty(ids)) {
      return new Page<>();
    }

    IPage<ProcessCase> listPage = processCaseRepositoryService
        .listPage(processCaseDTO, BaseContext.getSelectProjectId(), ids);

    return listPage.convert(processCase -> {
      ProcessCaseResult processCaseResult = processCaseResultRepositoryService
          .lastResult(BaseContext.getSelectProjectId(), processCase.getId());

      ProcessCaseVO processCaseVO = new ProcessCaseVO();
      BeanUtils.copyProperties(processCase, processCaseVO);

      processCaseVO.setPriorityDesc(PriorityEnum.getDescByCode(processCase.getPriority()));
      processCaseVO.setOwnerName(userRepository.getUserData(processCase.getOwnerId()).getName());
      Optional.ofNullable(processCaseResult).ifPresent(result -> {
        processCaseVO
            .setRecentExecResult(Optional.ofNullable(processCaseResult.getStatus()).orElse(""));
        processCaseVO
            .setRecentExecTime(Optional.ofNullable(processCaseResult.getCreateTime()).orElse(null));
      });
      return processCaseVO;
    });
  }

  @Override
  public Boolean save(ProcessCaseReq processCaseReq) {
    ProcessCase processCase = new ProcessCase();
    BeanUtils.copyProperties(processCaseReq, processCase);
    processCase.setProjectId(
        Optional.ofNullable(processCase.getProjectId()).orElse(BaseContext.getSelectProjectId()));
    processCase
        .setOwnerId(Optional.ofNullable(processCase.getOwnerId()).orElse(BaseContext.getUserId()));
    return processCaseRepositoryService.saveOrUpdate(processCase);
  }

  @Override
  public Boolean batchUpdateOwner(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateOwner(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateMove(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateMove(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateUpCase(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateUpCase(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateDownCase(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateDownCase(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean copy(ProcessCase processCase) {
    ProcessCase processCaseData = processCaseRepositoryService.getById(processCase.getId());
    processCaseData.setName(processCaseData.getName() + "-copy");
    processCaseData.setId(null);
    return processCaseRepositoryService.save(processCaseData);
  }

  @Override
  public Boolean delete(ProcessCase processCase) {
    return processCaseRepositoryService.removeById(processCase.getId());
  }
}
