package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ExecModeEnum;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.entity.req.ApiCaseResultReq;
import com.example.crazytest.vo.ApiCaseResultVO;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.ResultApiVO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 13:32
 * @DESRIPTION
 */

@Service
public class ApiCaseResultServiceImp implements ApiCaseResultService {

  @Autowired
  ApiCaseResultRepositoryService apiCaseResultRepositoryService;


  @Autowired
  ApiCaseRepositoryService apiCaseRepository;

  @Autowired
  ApiCaseRepositoryService apiCaseService;

  @Autowired
  UserRepositoryService userRepository;

  @Override
  public ApiCaseRecord queryById(Long id) {
    return apiCaseResultRepositoryService.getById(id);
  }

  @Override
  public IPage<ApiCaseResultReq> list(String apiTestcaseId, Integer current, Integer pageSize) {
    IPage<ApiCaseRecord> apiCaseResult = apiCaseResultRepositoryService
        .list(apiTestcaseId, current, pageSize);

    return apiCaseResult.convert(caseResult -> {
      ApiCaseResultReq apiCaseResultVo = new ApiCaseResultReq();
      BeanUtils.copyProperties(caseResult, apiCaseResultVo);
      return apiCaseResultVo;
    });
  }

  @Override
  public boolean save(ApiDebugReq apiDebugReq, ResultApiVO resultApiVO) {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());

    ApiCaseRecord apiCaseRecord = ApiCaseRecord.builder()
        .projectId(BaseContext.getSelectProjectId())
        .apiTestcaseId(apiDebugReq.getId())
        .caseOwnerId(apiCase.getOwnerId())
        .mode(Objects.isNull(apiDebugReq.getMode()) ? ExecModeEnum.MANUAL.getValue()
            : ExecModeEnum.AUTO.getValue())
        .status(Optional.ofNullable(resultApiVO.getAssertResultVo()).map(
            AssertResultVo::getPass).filter(pass -> pass)
            .map(pass -> ExecStatusEnum.SUCCESS.getValue()).orElse(ExecStatusEnum.FAIL.getValue()))
        .debugResult(JSON.toJSONString(resultApiVO))
        .scheduleId(apiDebugReq.getScheduleId())
        .scheduleBatchId(apiDebugReq.getScheduleBatchId())
        .build();
    return apiCaseResultRepositoryService.save(apiCaseRecord);
  }

  @Override
  public List<Long> listResult(Long projectId, String recentExecResult) {
    List<ApiCaseRecord> apiCaseRecords = apiCaseResultRepositoryService
        .listResult(projectId, recentExecResult);
    return apiCaseRecords.stream().map(ApiCaseRecord::getApiTestcaseId)
        .collect(Collectors.toList());
  }

  /**
   * 获取最近一次执行结果
   * @param scheduleBatchId
   * @return
   */
  @Override
  public List<Long> lastExecResult(Long scheduleBatchId) {
    List<ApiCaseRecord> apiCaseRecordList = apiCaseResultRepositoryService
        .lastExecResult(BaseContext.getSelectProjectId(), scheduleBatchId);

    return apiCaseRecordList.stream().map(ApiCaseRecord::getId)
        .collect(Collectors.toList());
  }

  /**
   * 根据任务id获取结果详情
   * @param apiCaseResultIds
   * @param current
   * @param pageSize
   * @return
   */
  @Override
  public IPage<ApiCaseRecord> resultList(List<Long> apiCaseResultIds, Integer current,
      Integer pageSize) {
    return apiCaseResultRepositoryService
        .resultList(BaseContext.getSelectProjectId(), apiCaseResultIds, current, pageSize);
  }

  /**
   * 获取任务列表详情
   * @param scheduleBatchId
   * @param current
   * @param pageSize
   * @return
   */
  @Override
  public IPage<ApiCaseResultVO> getApiResultDetail(Long scheduleBatchId, Integer current,
      Integer pageSize) {
    List<Long> ids = lastExecResult(scheduleBatchId);
    IPage<ApiCaseRecord> apiCaseRecordIPage = resultList(ids, current, pageSize);

    return apiCaseRecordIPage.convert(apiCaseRecord -> {
      ApiCaseResultVO apiCaseRecordVO = new ApiCaseResultVO();
      BeanUtils.copyProperties(apiCaseRecord, apiCaseRecordVO);

      ApiCase apiCase = apiCaseService.getById(apiCaseRecord.getApiTestcaseId());
      List<ApiCaseRecord> apiCaseRecordList = apiCaseResultRepositoryService
          .getResultChildren(BaseContext.getSelectProjectId(), scheduleBatchId,
              apiCaseRecord.getApiTestcaseId(), apiCaseRecord.getId());
      User user = userRepository.getById(apiCase.getOwnerId());

      List<ApiCaseResultVO> apiCaseRecordChildren = getApiCaseRecordChildrenCovert(
          apiCaseRecordList, user.getName(), apiCase.getName());

      apiCaseRecordVO.setOwnerName(user.getName());
      apiCaseRecordVO.setCaseName(apiCase.getName());
      apiCaseRecordVO.setChildren(apiCaseRecordChildren);
      return apiCaseRecordVO;
    });
  }

  /**
   * 获取用例执行结果详情 整理子集
   * @param apiCaseRecordList
   * @param ownerName
   * @param caseName
   * @return
   */
  @Override
  public List<ApiCaseResultVO> getApiCaseRecordChildrenCovert(List<ApiCaseRecord> apiCaseRecordList,
      String ownerName, String caseName) {
    return apiCaseRecordList.stream().map(apiCaseRecord -> {
      ApiCaseResultVO apiCaseResultVO = new ApiCaseResultVO();
      BeanUtils.copyProperties(apiCaseRecord, apiCaseResultVO);
      apiCaseResultVO.setOwnerName(ownerName);
      apiCaseResultVO.setCaseName(caseName);
      return apiCaseResultVO;
    }).collect(Collectors.toList());
  }
}
