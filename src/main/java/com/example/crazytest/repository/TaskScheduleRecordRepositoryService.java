package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 定时任务执行记录表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-13
 */
public interface TaskScheduleRecordRepositoryService extends IService<TaskScheduleRecord> {

  IPage<TaskScheduleRecord> listPage(Long projectId, Long scheduleId, Long scheduleBatchId,
      Integer current, Integer pageSize);

  IPage<TaskScheduleRecord> listPageResult(Long projectId,  Long scheduleId, List<Long> ids, Integer current, Integer pageSize);

}
