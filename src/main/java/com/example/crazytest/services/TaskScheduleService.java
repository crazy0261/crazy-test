package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.req.TaskScheduleReq;
import com.example.crazytest.vo.TaskScheduleVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.List;

public interface TaskScheduleService {

  IPage<TaskScheduleVO> list(String name, String testcaseType, String ownerName, Integer enable,
      Integer current, Integer pageSize);

  Boolean save(TaskScheduleReq taskScheduleReq) throws JsonProcessingException;

  TaskScheduleVO queryById(Long id) throws JsonProcessingException;

  Boolean delete(Long id);

  void execute(Long id, String mode) throws IOException;

  List<Long> listAllEnable();


}
