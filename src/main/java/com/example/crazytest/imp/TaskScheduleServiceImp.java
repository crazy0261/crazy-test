package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.convert.ApiCaseConvert;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ExecModeEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.TaskScheduleService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.CronUtil;
import com.example.crazytest.utils.TimestampRandomIdGenerator;
import com.example.crazytest.vo.ResultApiVO;
import com.example.crazytest.vo.TaskScheduleVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @Autowired
  ApiCaseService apiCaseService;

  @Autowired
  UserService userService;

  @Autowired
  ApiCaseConvert caseConvert;

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
  public Boolean save(TaskSchedule taskSchedule) throws JsonProcessingException {
    List<TaskSchedule> taskSchedules = repositoryService.cheTaskSchedule(taskSchedule.getName());

    AssertUtil.assertTrue(Objects.isNull(taskSchedule.getId()) && Objects.nonNull(taskSchedules),
        "任务名称已存在");
    CronUtil.cronCheckRule(taskSchedule.getCron());

    taskSchedule.setNextExecTime(CronUtil.getNextTime(taskSchedule.getCron()));

    String apiCaseCheckEnable = caseConvert.apiCaseCheckEnable(taskSchedule.getTestcaseList());

    taskSchedule.setTenantId(
        Optional.ofNullable(taskSchedule.getTenantId()).orElse(BaseContext.getTenantId()));
    taskSchedule
        .setOwnerId(Optional.ofNullable(taskSchedule.getOwnerId()).orElse(BaseContext.getUserId()));
    taskSchedule.setTestcaseList(apiCaseCheckEnable);
    return repositoryService.saveOrUpdate(taskSchedule);
  }

  @Override
  public TaskScheduleVO queryById(Long id) throws JsonProcessingException {
    TaskScheduleVO taskScheduleVO = new TaskScheduleVO();

    TaskSchedule taskSchedule = repositoryService.getById(id);
    Long apiCaseCount = apiCaseRepositoryService.countCase();
    List<Long> apiCaseList = caseConvert.apiCaseIdTypeConvert(taskSchedule.getTestcaseList());

    BeanUtils.copyProperties(taskSchedule, taskScheduleVO);
    taskScheduleVO.setOwnerName(userService.getById(taskSchedule.getOwnerId()).getName());
    taskScheduleVO.setApiCaseIds(apiCaseList);
    taskScheduleVO.setIsAllCase(Objects.equals(apiCaseCount, (long) apiCaseList.size()));
    return taskScheduleVO;
  }

  @Override
  public Boolean delete(Long id) {
    return repositoryService.removeById(id);
  }

  @Override
  @Async("taskThreadPool")
  public void execute(Long id) throws IOException {
    TaskSchedule taskSchedule = repositoryService.getById(id);
    ApiDebugReq apiDebugReq = new ApiDebugReq();
    apiDebugReq.setScheduleBatchId(TimestampRandomIdGenerator.generateId());
    apiDebugReq.setMode(ExecModeEnum.AUTO.getDesc());
    apiDebugReq.setEnvId(Long.valueOf(taskSchedule.getEnv()));
    apiDebugReq.setScheduleId(taskSchedule.getId());

    if (Objects.equals("API_CASE", taskSchedule.getTestcaseType())) {
      ResultApiVO resultApi = apiCaseService.debug(apiDebugReq);
      apiCaseResultService.save(apiDebugReq, resultApi);
    } else {
      // todo 场景用例
    }
  }

  @Override
  public List<Long> listAllEnable() {
    List<TaskSchedule> taskSchedules = repositoryService.listAllEnable();
    return taskSchedules.stream().map(TaskSchedule::getId).collect(Collectors.toList());
  }
}
