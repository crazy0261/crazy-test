package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.mapper.ProcessCaseMapper;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Service
public class ProcessCaseRepositoryServiceImpl extends
    ServiceImpl<ProcessCaseMapper, ProcessCase> implements
    ProcessCaseRepositoryService {

  @Override
  public IPage<ProcessCase> listPage(ProcessCaseDTO processCaseDTO, Long projectId,
      List<Long> ids) {
    return this.lambdaQuery()
        .eq(ObjectUtils.isNotNull(processCaseDTO.getTreeKey()), ProcessCase::getTreeKey, processCaseDTO.getTreeKey())
        .eq(ProcessCase::getProjectId, projectId)
        .in(ObjectUtils.isNotNull(ids), ProcessCase::getId, ids)
        .like(ObjectUtils.isNotNull(processCaseDTO.getName()), ProcessCase::getName, processCaseDTO.getName())
        .eq(ObjectUtils.isNotNull(processCaseDTO.getOwnerId()), ProcessCase::getOwnerId, processCaseDTO.getOwnerId())
        .eq(ObjectUtils.isNotNull(processCaseDTO.getIsSubProcess()), ProcessCase::getIsSubProcess, processCaseDTO.getIsSubProcess())
        .orderByDesc(ProcessCase::getUpdateTime)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .page(new Page<>(processCaseDTO.getCurrent(), processCaseDTO.getPageSize()));
  }
}
