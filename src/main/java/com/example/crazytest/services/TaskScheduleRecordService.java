package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import java.util.List;

public interface TaskScheduleRecordService {


  IPage<TaskScheduleRecordVO> listPage(Long scheduleId, Integer current, Integer pageSize);

  IPage<TaskScheduleRecord> queryBatchExecResult(Long scheduleId, List<Long> ids, Integer current, Integer pageSize);


}
