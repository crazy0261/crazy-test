package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-28
 */
public interface TaskScheduleRepositoryService extends IService<TaskSchedule> {

  IPage<TaskSchedule> list(String name, String testcaseType, List<Long> ownerId, Integer enable,
      Integer current, Integer pageSize);

  List<TaskSchedule> cheTaskSchedule(String name);

}
