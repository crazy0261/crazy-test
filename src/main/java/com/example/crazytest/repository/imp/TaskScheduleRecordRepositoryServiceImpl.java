package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.mapper.TaskScheduleRecordMapper;
import com.example.crazytest.repository.TaskScheduleRecordRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务执行记录表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-13
 */
@Service
public class TaskScheduleRecordRepositoryServiceImpl extends
    ServiceImpl<TaskScheduleRecordMapper, TaskScheduleRecord> implements
    TaskScheduleRecordRepositoryService {

  @Override
  public IPage<TaskScheduleRecord> listPage(Long projectId, Long scheduleId, Integer current,
      Integer pageSize) {
    return this.lambdaQuery()
        .eq(TaskScheduleRecord::getProjectId, projectId)
        .eq(Objects.nonNull(scheduleId), TaskScheduleRecord::getScheduleId, scheduleId)
        .eq(TaskScheduleRecord::getIsDelete, Boolean.FALSE)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public IPage<TaskScheduleRecord> listPageResult(Long projectId, Long scheduleId, List<Long> ids,
      Integer current, Integer pageSize) {
    return this.lambdaQuery()
        .eq(TaskScheduleRecord::getProjectId, projectId)
        .eq(Objects.nonNull(scheduleId), TaskScheduleRecord::getScheduleId, scheduleId)
        .in(TaskScheduleRecord::getId, ids)
        .orderByDesc(TaskScheduleRecord::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }
}
