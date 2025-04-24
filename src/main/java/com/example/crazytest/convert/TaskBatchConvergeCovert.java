package com.example.crazytest.convert;

import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.vo.TaskBatchConvergeVO;
import java.util.Map;

/**
 * @author
 * @name Menghui
 * @date 2025/4/24 22:30
 * @DESRIPTION
 */


public class TaskBatchConvergeCovert {

  /**
   * 任务批量转换
   * @param taskSchedule
   * @param taskScheduleRecords
   * @param countStatusByCaseIds
   * @return
   */
  public static TaskBatchConvergeVO taskBatchConverge(TaskSchedule taskSchedule,
      TaskScheduleRecord taskScheduleRecords, Map<String, Long> countStatusByCaseIds) {

    TaskBatchConvergeVO batchConvergeVO = new TaskBatchConvergeVO();

    batchConvergeVO.setScheduleId(taskSchedule.getId());
    batchConvergeVO.setScheduleName(taskSchedule.getName());
    batchConvergeVO.setScheduleBatchId(taskScheduleRecords.getScheduleBatchId());
    batchConvergeVO.setEnvSortId(taskScheduleRecords.getEnvSortId());
    batchConvergeVO.setStatus(taskScheduleRecords.getStatus());
    batchConvergeVO
        .setTotalCount(countStatusByCaseIds.values().stream().mapToLong(Long::longValue).sum());
    batchConvergeVO
        .setSuccessCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.SUCCESS.name(), 0L));
    batchConvergeVO
        .setFailCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.FAILED.name(), 0L));
    batchConvergeVO
        .setRunningCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.RUNNING.name(), 0L));
    batchConvergeVO.setInitCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.INIT.name(), 0L));
    batchConvergeVO
        .setIgnoreCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.IGNORE.name(), 0L));
    batchConvergeVO
        .setTimeoutCount(countStatusByCaseIds.getOrDefault(ExecStatusEnum.TIMEOUT.name(), 0L));
    return batchConvergeVO;
  }

}
