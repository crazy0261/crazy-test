package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.vo.CaseResultDetailVO;
import com.example.crazytest.vo.TaskBatchConvergeVO;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TaskScheduleRecordService {


  IPage<TaskScheduleRecordVO> listPage(Long scheduleId, Integer current, Integer pageSize);

  CaseResultDetailVO scheduleBatchList(Long scheduleId, String scheduleBatchId, Integer current, Integer pageSize);

  TaskBatchConvergeVO taskBatchConverge(Long scheduleId, String scheduleBatchId)
      throws JsonProcessingException;


}
