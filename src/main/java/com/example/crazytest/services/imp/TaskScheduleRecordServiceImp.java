package com.example.crazytest.services.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.enums.CaseTypeEnums;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.repository.TaskScheduleRecordRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ProcessCaseResultService;
import com.example.crazytest.services.TaskScheduleRecordService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ApiCaseResultVO;
import com.example.crazytest.vo.CaseResultDetailVO;
import com.example.crazytest.vo.ProcessCaseResultDetailVO;
import com.example.crazytest.vo.TaskBatchConvergeVO;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/13 23:07
 * @DESRIPTION
 */

@Service
public class TaskScheduleRecordServiceImp implements TaskScheduleRecordService {

  @Autowired
  TaskScheduleRecordRepositoryService taskScheduleRecordRepositoryService;

  @Autowired
  EnvConfigRepositoryService envConfigRepositoryService;

  @Autowired
  TaskScheduleRepositoryService taskScheduleRepositoryService;

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @Autowired
  ProcessCaseResultService processCaseResultService;

  /**
   * 任务记录列表
   *
   * @param scheduleId
   * @param current
   * @param pageSize
   * @return
   */
  @Override
  public IPage<TaskScheduleRecordVO> listPage(Long scheduleId, Integer current, Integer pageSize) {
    IPage<TaskScheduleRecord> page = taskScheduleRecordRepositoryService
        .listPage(BaseContext.getSelectProjectId(), scheduleId, current, pageSize);

    return page.convert(taskScheduleRecord -> {
      TaskScheduleRecordVO taskScheduleRecordVO = new TaskScheduleRecordVO();
      EnvConfig envConfig = envConfigRepositoryService.getById(taskScheduleRecord.getEnvSortId());
      TaskSchedule taskSchedule = taskScheduleRepositoryService
          .getById(taskScheduleRecord.getScheduleId());
      BeanUtils.copyProperties(taskScheduleRecord, taskScheduleRecordVO);
      taskScheduleRecordVO
          .setScheduleBatchId(String.valueOf(taskScheduleRecord.getScheduleBatchId()));
      taskScheduleRecordVO.setEnvName(envConfig.getEnvName());
      taskScheduleRecordVO.setScheduleName(taskSchedule.getName());
      return taskScheduleRecordVO;
    });
  }

  /**
   * 批次任务用例详情列表
   *
   * @param scheduleId
   * @param scheduleBatchId
   * @param current
   * @param pageSize
   * @return
   */
  @Override
  public CaseResultDetailVO scheduleBatchList(Long scheduleId, String scheduleBatchId,
      Integer current, Integer pageSize) {
    CaseResultDetailVO caseResult = new CaseResultDetailVO();
    TaskSchedule taskSchedule = taskScheduleRepositoryService.getById(scheduleId);

    if (Objects.equals(CaseTypeEnums.API_CASE_TYPE.getType(), taskSchedule.getTestcaseType())) {
      IPage<ApiCaseResultVO> apiCaseResult = apiCaseResultService
          .getApiResultDetail(Long.valueOf(scheduleBatchId), current, pageSize);
      caseResult.setApiResultDetail(apiCaseResult);
      caseResult.setTotal(Optional.ofNullable(apiCaseResult).map(IPage::getTotal).orElse(0L));
      return caseResult;
    } else if (Objects
        .equals(CaseTypeEnums.PROCESS_CASE_TYPE.getType(), taskSchedule.getTestcaseType())) {
      IPage<ProcessCaseResultDetailVO> processCaseResult = processCaseResultService
          .getProcessCaseResultDetail(scheduleBatchId, current, pageSize);
      caseResult.setProcessCaseResultDetail(processCaseResult);
      caseResult.setTotal(Optional.ofNullable(processCaseResult).map(IPage::getTotal).orElse(0L));
      return caseResult;
    }
    return caseResult;
  }

  /**
   * 任务批次聚合
   *
   * @param scheduleId
   * @param scheduleBatchId
   * @return
   */
  @Override
  public TaskBatchConvergeVO taskBatchConverge(Long scheduleId, String scheduleBatchId)
      throws JsonProcessingException {
    Long scheduleBatch = Long.valueOf(scheduleBatchId);
    TaskSchedule taskSchedule = taskScheduleRepositoryService.getById(scheduleId);
    TaskScheduleRecord taskScheduleRecords = taskScheduleRecordRepositoryService
        .listByScheduleBatch(scheduleId, scheduleBatch);

    if (Objects.equals(CaseTypeEnums.API_CASE_TYPE.getType(), taskSchedule.getTestcaseType())) {
      return apiCaseResultService.taskBatchConverge(taskSchedule, taskScheduleRecords);
    } else {
      return processCaseResultService.taskBatchConverge(taskSchedule, taskScheduleRecords);
    }
  }

}
