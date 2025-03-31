package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.vo.TaskScheduleVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface TaskScheduleService {

  IPage<TaskScheduleVO> list(String name, String testcaseType, String ownerName, Integer enable,
      Integer current, Integer pageSize);

  Boolean save(TaskSchedule taskSchedule) throws JsonProcessingException;

  TaskScheduleVO queryById(Long id) throws JsonProcessingException;

  Boolean delete(Long id);

  Boolean execute(List<Long> ids);


}
