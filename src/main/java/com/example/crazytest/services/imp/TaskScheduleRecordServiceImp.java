package com.example.crazytest.services.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.repository.TaskScheduleRecordRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.TaskScheduleRecordService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import java.util.List;
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

  @Override
  public IPage<TaskScheduleRecordVO> listPage(Long scheduleId, Long scheduleBatchId,
      Integer current, Integer pageSize) {
    IPage<TaskScheduleRecord> page =taskScheduleRecordRepositoryService.listPage(BaseContext.getSelectProjectId(), scheduleId, scheduleBatchId, current, pageSize);

    return page.convert(taskScheduleRecord -> {
      TaskScheduleRecordVO taskScheduleRecordVO = new TaskScheduleRecordVO();
      EnvConfig envConfig = envConfigRepositoryService.getById(taskScheduleRecord.getEnvSortId());
      TaskSchedule taskSchedule = taskScheduleRepositoryService
          .getById(taskScheduleRecord.getScheduleId());
      BeanUtils.copyProperties(taskScheduleRecord, taskScheduleRecordVO);
      taskScheduleRecordVO.setEnvName(envConfig.getEnvName());
      taskScheduleRecordVO.setScheduleName(taskSchedule.getName());
      return taskScheduleRecordVO;
    });
  }

  @Override
  public IPage<TaskScheduleRecord> queryBatchExecResult(Long scheduleId, List<Long> ids, Integer current, Integer pageSize) {
    return taskScheduleRecordRepositoryService.listPageResult(BaseContext.getSelectProjectId(), scheduleId, ids, current, pageSize);
  }


}
