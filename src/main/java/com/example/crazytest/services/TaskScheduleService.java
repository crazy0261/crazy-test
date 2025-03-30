package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.vo.TaskScheduleVO;

public interface TaskScheduleService {

  IPage<TaskScheduleVO> list(String name, String testcaseType, String ownerName, Integer enable,
      Integer current, Integer pageSize);

  Boolean save(TaskSchedule taskSchedule);

  TaskScheduleVO queryById(Long id);

  Boolean delete(Long id);



}
