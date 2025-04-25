package com.example.crazytest.convert;

import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.repository.TaskScheduleRecordRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 15:37
 * @DESRIPTION
 */

@Service
public class ApiCaseConvert {

  @Autowired
  ApiCaseService caseService;

  @Autowired
  TaskScheduleRecordRepositoryService taskScheduleRecordRepositoryService;

  /**
   * apiCaseIds 转换
   *
   * @param apiCaseIds
   * @return
   * @throws JsonProcessingException
   */
  public List<Long> apiCaseIdTypeConvert(String apiCaseIds) throws JsonProcessingException {

    if (StringUtils.isEmpty(apiCaseIds)) {
      return new ArrayList<>();
    }

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(apiCaseIds, new TypeReference<List<Long>>() {
    });
  }

  /**
   * apiCaseIds 检查是否可用
   *
   * @param apiCaseIds
   * @return
   * @throws JsonProcessingException
   */
  public String apiCaseCheckEnable(List<Long> apiCaseIds) throws JsonProcessingException {
    List<Long> apiCaseIdsList = apiCaseIdTypeConvert(apiCaseIds.toString());
    return caseService.checkApiCaseEnable(apiCaseIdsList).toString();
  }

  /**
   * apiCaseTaskRecordSave
   *
   * @param taskSchedule
   * @param scheduleBatchId
   * @param status
   */
  public void apiCaseTaskRecordSave(TaskSchedule taskSchedule, Long scheduleBatchId,
      String status, String mode) {
    TaskScheduleRecord taskScheduleRecord = new TaskScheduleRecord();
    taskScheduleRecord.setProjectId(taskSchedule.getProjectId());
    taskScheduleRecord.setScheduleId(taskSchedule.getId());
    taskScheduleRecord.setScheduleBatchId(scheduleBatchId);
    taskScheduleRecord.setEnvSortId(taskSchedule.getEnvSort());
    taskScheduleRecord.setStatus(status);
    taskScheduleRecord.setMode(mode);
    taskScheduleRecord.setTestcaseType(taskSchedule.getTestcaseType());
    taskScheduleRecord.setTestcaseList(taskSchedule.getTestcaseList());

    taskScheduleRecordRepositoryService.saveOrUpdate(taskScheduleRecord);
  }

}
