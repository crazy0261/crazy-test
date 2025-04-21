package com.example.crazytest.services.imp;

import com.example.crazytest.convert.ApiCaseConvert;
import com.example.crazytest.dto.TaskDailyDTO;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.DataTaskCountEntity;
import com.example.crazytest.entity.NotTaskEntity;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.User;
import com.example.crazytest.enums.CaseTypeEnums;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.NotTaskService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.StatisticsDetailVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/21 16:34
 * @DESRIPTION
 */

@Service
public class NotTaskServiceImp implements NotTaskService {

  @Autowired
  ApiCaseConvert caseConvert;

  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  TaskScheduleRepositoryService taskScheduleRepositoryService;

  @Autowired
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Override
  public StatisticsDetailVO getDailyTask() {
    StatisticsDetailVO statisticsDetailVO = new StatisticsDetailVO();
    TaskDailyDTO taskDaily = this.taskDailyService();
    statisticsDetailVO.setNotTaskCount(this.notTaskCount(taskDaily));
    statisticsDetailVO.setNotTaskList(this.notTaskList(taskDaily));
    return statisticsDetailVO;
  }

  /**
   * 获取未完成任务列表
   *
   * @return
   */
  @Override
  public List<DataTaskCountEntity> notTaskCount(TaskDailyDTO taskDaily) {

    return this.mergeDataCountTaskEntities(
        buildDataCountEntities(taskDaily.getNotTaskApiCases()),
        buildDataCountEntities(taskDaily.getNotTaskProcessCases())
    );
  }

  /**
   * 获取未完成任务列表明细
   *
   * @return
   */
  @Override
  public List<NotTaskEntity> notTaskList(TaskDailyDTO taskDaily) {
    List<NotTaskEntity> notTaskList = new ArrayList<>();
    taskDaily.getNotTaskApiCases().forEach(apiCase -> {
      NotTaskEntity taskEntity = new NotTaskEntity();
      User user = userRepositoryService.getById(apiCase.getOwnerId());
      taskEntity.setId(apiCase.getId());
      taskEntity.setName(apiCase.getName());
      taskEntity.setType(CaseTypeEnums.API_CASE_TYPE.getType());
      taskEntity.setOwnerName(user.getName());
      notTaskList.add(taskEntity);
    });

    taskDaily.getNotTaskProcessCases().forEach(processCase -> {
      NotTaskEntity taskEntity = new NotTaskEntity();
      User user = userRepositoryService.getById(processCase.getOwnerId());
      taskEntity.setId(processCase.getId());
      taskEntity.setName(processCase.getName());
      taskEntity.setType(CaseTypeEnums.PROCESS_CASE_TYPE.getType());
      taskEntity.setOwnerName(user.getName());
      notTaskList.add(taskEntity);
    });

    return notTaskList;
  }


  /**
   * 获取任务中未完成的用例
   *
   * @return
   */
  @Override
  public TaskDailyDTO taskDailyService() {
    TaskDailyDTO taskDailyDTO = new TaskDailyDTO();
    Long projectId = BaseContext.getSelectProjectId();

    List<Long> apiCaseIds = extractCaseIds(
        taskScheduleRepositoryService.queryApiCaseList(projectId));
    List<Long> processCaseIds = extractCaseIds(
        taskScheduleRepositoryService.queryProcessList(projectId));

    List<ApiCase> allApiCases = apiCaseRepositoryService.allList(projectId);
    List<ProcessCase> allProcessCases = processCaseRepositoryService.getAllList(projectId);

    List<ApiCase> notTaskApiCases = getNotTaskCases(allApiCases, apiCaseIds);
    List<ProcessCase> notTaskProcessCases = getNotTaskCases(allProcessCases, processCaseIds);

    taskDailyDTO.setNotTaskApiCases(notTaskApiCases);
    taskDailyDTO.setNotTaskProcessCases(notTaskProcessCases);
    return taskDailyDTO;
  }

  /**
   * 提取任务中的用例id
   *
   * @param taskSchedules
   * @return
   */
  @Override
  public List<Long> extractCaseIds(List<TaskSchedule> taskSchedules) {
    return taskSchedules.stream()
        .flatMap(taskSchedule -> {
          try {
            return caseConvert.apiCaseIdTypeConvert(taskSchedule.getTestcaseList()).stream();
          } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Stream.empty();
          }
        })
        .collect(Collectors.toList());
  }

  /**
   * 获取不在任务中的用例
   *
   * @param allCases
   * @param taskCaseIds
   * @return
   */
  @Override
  public <T> List<T> getNotTaskCases(List<T> allCases, List<Long> taskCaseIds) {
    Set<Long> taskCaseIdSet = new HashSet<>(taskCaseIds);
    return allCases.stream()
        .filter(caseItem -> !taskCaseIdSet.contains(getId(caseItem)))
        .collect(Collectors.toList());
  }

  /**
   * 获取用例id
   *
   * @param caseItem
   * @return
   */
  @Override
  public Long getId(Object caseItem) {
    if (caseItem instanceof ApiCase) {
      return ((ApiCase) caseItem).getId();
    } else if (caseItem instanceof ProcessCase) {
      return ((ProcessCase) caseItem).getId();
    } else {
      throw new IllegalArgumentException(
          ResultEnum.INTERNAL_SERVER_ERROR.getMessage() + caseItem.getClass());
    }
  }

  /**
   * 构建数据统计实体
   * @param caseList
   * @return
   */
  @Override
  public List<DataTaskCountEntity> buildDataCountEntities(List<?> caseList) {
    Map<String, Long> nameCountMap = caseList.stream()
        .collect(Collectors.groupingBy(
            this::getAppName,
            Collectors.counting()
        ));

    return nameCountMap.entrySet().stream()
        .map(entry -> new DataTaskCountEntity(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

  }

  /**
   * 获取应用名称
   *
   * @param caseItem
   * @return
   */
  @Override
  public String getAppName(Object caseItem) {
    if (caseItem instanceof ApiCase) {
      Long appId = ((ApiCase) caseItem).getAppId();
      ApplicationManagement applicationManagement = applicationManagementRepositoryService
          .getById(appId);
      return Optional.ofNullable(applicationManagement).map(ApplicationManagement::getName)
          .orElse("其它");
    } else if (caseItem instanceof ProcessCase) {
      Long appId = ((ProcessCase) caseItem).getAppId();
      ApplicationManagement applicationManagement = applicationManagementRepositoryService
          .getById(appId);
      return Optional.ofNullable(applicationManagement).map(ApplicationManagement::getName)
          .orElse("其它");
    } else {
      throw new IllegalArgumentException(
          ResultEnum.INTERNAL_SERVER_ERROR.getMessage() + caseItem.getClass());
    }
  }

  /**
   * 合并任务中未完成的用例
   * @param apiCaseList
   * @param processCaseList
   * @return
   */
  @Override
  public List<DataTaskCountEntity> mergeDataCountTaskEntities(List<DataTaskCountEntity> apiCaseList,
      List<DataTaskCountEntity> processCaseList) {
    Map<String, Long> mergedMap = new HashMap<>();

    apiCaseList.forEach(entity -> mergedMap.merge(entity.getAppName(), entity.getCount(), Long::sum));
    processCaseList.forEach(entity -> mergedMap.merge(entity.getAppName(), entity.getCount(), Long::sum));

    return mergedMap.entrySet().stream()
        .map(entry -> new DataTaskCountEntity(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
