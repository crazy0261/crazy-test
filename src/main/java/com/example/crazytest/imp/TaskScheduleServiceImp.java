package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.TaskScheduleService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.vo.TaskScheduleVO;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 16:31
 * @DESRIPTION
 */

public class TaskScheduleServiceImp implements TaskScheduleService {

  @Autowired
  TaskScheduleRepositoryService repositoryService;

  @Autowired
  UserService userService;

  @Override
  public IPage<TaskScheduleVO> list(String name, String testcaseType, String ownerName,
      Integer enable, Integer current, Integer pageSize) {
    List<Long> userIds = userService.getNameList(ownerName);

    IPage<TaskSchedule> taskSchedulePage = repositoryService
        .list(name, testcaseType, userIds, enable, current, pageSize);

    return taskSchedulePage.convert(taskSchedule -> {
      TaskScheduleVO taskScheduleVO = new TaskScheduleVO();
      BeanUtils.copyProperties(taskSchedule, taskScheduleVO);
      taskScheduleVO.setOwnerName(userService.getById(taskSchedule.getOwnerId()).getName());
      return taskScheduleVO;
    });
  }
}
