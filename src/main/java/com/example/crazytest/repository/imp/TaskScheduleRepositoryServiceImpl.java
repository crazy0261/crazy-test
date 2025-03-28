package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.mapper.TaskScheduleMapper;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-28
 */
@Service
public class TaskScheduleRepositoryServiceImpl extends
    ServiceImpl<TaskScheduleMapper, TaskSchedule> implements
    TaskScheduleRepositoryService {

  @Override
  public IPage<TaskSchedule> list(String name, String testcaseType, List<Long> ownerId,
      Integer enable, Integer current, Integer pageSize) {

    return this.lambdaQuery()
        .eq(TaskSchedule::getTenantId, BaseContext.getTenantId())
        .like(ObjectUtils.isNotNull(name), TaskSchedule::getName, name)
        .eq(ObjectUtils.isNotNull(testcaseType), TaskSchedule::getTestcaseType, testcaseType)
        .in(ObjectUtils.isNotNull(ownerId), TaskSchedule::getOwnerId, ownerId)
        .eq(ObjectUtils.isNotNull(enable), TaskSchedule::getEnable, enable)
        .eq(TaskSchedule::getIsDelete, Boolean.FALSE)
        .orderByDesc(TaskSchedule::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<TaskSchedule> cheTaskSchedule(String name) {
    return this.lambdaQuery().eq(TaskSchedule::getIsDelete, Boolean.FALSE)
        .eq(TaskSchedule::getName, name).list();
  }
}
