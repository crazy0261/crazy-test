package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.TaskScheduleService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.TaskScheduleVO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 16:31
 * @DESRIPTION
 */

@Service
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

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean save(TaskSchedule taskSchedule) {
    List<TaskSchedule> taskSchedules =repositoryService.cheTaskSchedule(taskSchedule.getName());
    AssertUtil.assertTrue(taskSchedules.isEmpty(),"任务名称已存在");

    taskSchedule.setTenantId(
        Optional.ofNullable(taskSchedule.getTenantId()).orElse(BaseContext.getTenantId()));
    taskSchedule
        .setOwnerId(Optional.ofNullable(taskSchedule.getOwnerId()).orElse(BaseContext.getUserId()));
    return repositoryService.saveOrUpdate(taskSchedule);
  }
}
