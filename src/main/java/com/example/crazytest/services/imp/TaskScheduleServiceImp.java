package com.example.crazytest.services.imp;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.convert.ApiCaseConvert;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.TaskScheduleReq;
import com.example.crazytest.enums.ExecModeEnum;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.ProcessCaseService;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class TaskScheduleServiceImp implements TaskScheduleService {

  @Autowired
  TaskScheduleRepositoryService repositoryService;


  @Autowired
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Autowired
  ProcessCaseRepositoryService processorService;

  @Autowired
  ProcessCaseService processCaseService;

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
  public Boolean save(TaskScheduleReq taskScheduleReq) throws JsonProcessingException {
    List<TaskSchedule> taskSchedules = repositoryService.cheTaskSchedule(taskScheduleReq.getName());

    AssertUtil
        .assertTrue(Objects.isNull(taskScheduleReq.getId()) && CollUtil.isNotEmpty(taskSchedules),
            ResultEnum.TASK_NAME_EXIST.getMessage());
    CronUtil.cronCheckRule(taskScheduleReq.getCron());

    TaskSchedule taskSchedule = new TaskSchedule();
    BeanUtils.copyProperties(taskScheduleReq, taskSchedule);

    taskSchedule.setNextExecTime(CronUtil.getNextTime(taskSchedule.getCron()));

    taskSchedule.setProjectId(
        Optional.ofNullable(taskSchedule.getProjectId()).orElse(BaseContext.getSelectProjectId()));
    taskSchedule
        .setOwnerId(Optional.ofNullable(taskSchedule.getOwnerId()).orElse(BaseContext.getUserId()));
    taskSchedule.setTestcaseList(Optional.ofNullable(taskScheduleReq.getTestcaseList()).map(
        Object::toString).orElse("[]"));
    return repositoryService.saveOrUpdate(taskSchedule);
  }

  @Override
  public TaskScheduleVO queryById(Long id) throws JsonProcessingException {
    TaskScheduleVO taskScheduleVO = new TaskScheduleVO();

    TaskSchedule taskSchedule = repositoryService.getById(id);
    Long apiCaseCount =
        Objects.equals("API_CASE", taskSchedule.getTestcaseType()) ? apiCaseRepositoryService
            .countCase(BaseContext.getSelectProjectId())
            : processorService.getProcessCount(BaseContext.getSelectProjectId(), Boolean.FALSE);

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
  public void execute(Long id, String mode) throws IOException {
    TaskSchedule taskSchedule = repositoryService.getById(id);

    ApiDebugReq apiDebugReq = new ApiDebugReq();
    apiDebugReq.setScheduleBatchId(TimestampRandomIdGenerator.generateId());
    apiDebugReq.setMode(ExecModeEnum.AUTO.getDesc());
    apiDebugReq.setEnvSortId(taskSchedule.getEnvSort());
    apiDebugReq.setScheduleId(taskSchedule.getId());

    String testcaseList = taskSchedule.getTestcaseList();
    List<Long> caseIds = caseConvert.apiCaseIdTypeConvert(testcaseList);

    // 存储所有失败
    Map<Long, String> failedTasks = Collections.synchronizedMap(new HashMap<>());

    if (Objects.equals("API_CASE", taskSchedule.getTestcaseType())) {

      CompletableFuture.allOf(caseIds.stream().map(caseId -> {
        apiDebugReq.setId(caseId);
        return CompletableFuture.runAsync(() -> {
          try {
            ResultApiVO resultApi = apiCaseService.debug(apiDebugReq);
            apiCaseResultService.save(apiDebugReq, resultApi);
          } catch (IOException e) {
            failedTasks.put(caseId, e.getMessage());
            log.info("task-id:{},api-case-executeTaskJob：{}", taskSchedule, e.getMessage());
          }
        });
      }).toArray(CompletableFuture[]::new)).join();

    } else {
      CompletableFuture.allOf(caseIds.stream().map(caseId -> {
        apiDebugReq.setId(caseId);
        return CompletableFuture.runAsync(() -> processCaseService.debug(apiDebugReq));
      }).toArray(CompletableFuture[]::new)).join();
    }

    caseConvert
        .apiCaseTaskRecordSave(taskSchedule, apiDebugReq.getScheduleBatchId(),
            failedTasks.isEmpty() ? ExecStatusEnum.SUCCESS.name() : ExecStatusEnum.FAILED.name(),
            mode);
  }

  @Override
  public List<Long> listAllEnable() {
    List<TaskSchedule> taskSchedules = repositoryService.listAllEnable();
    return taskSchedules.stream().map(TaskSchedule::getId).collect(Collectors.toList());
  }
}
