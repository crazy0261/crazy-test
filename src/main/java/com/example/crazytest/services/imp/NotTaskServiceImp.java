package com.example.crazytest.services.imp;

import com.example.crazytest.convert.ApiCaseConvert;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.User;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.TaskScheduleRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.NotTaskService;
import com.example.crazytest.utils.BaseContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  /**
   * 获取未完成任务列表
   * @return
   */
  @Override
  public List<DataCountEntity> notTaskCount() {
    Long projectId = BaseContext.getSelectProjectId();

    List<Long> apiCaseIds = extractCaseIds(
        taskScheduleRepositoryService.queryApiCaseList(projectId));
    List<Long> processCaseIds = extractCaseIds(
        taskScheduleRepositoryService.queryProcessList(projectId));

    List<ApiCase> allApiCases = apiCaseRepositoryService.allList(projectId);
    List<ProcessCase> allProcessCases = processCaseRepositoryService.getAllList(projectId);

    List<ApiCase> notTaskApiCases = getNotTaskCases(allApiCases, apiCaseIds);
    List<ProcessCase> notTaskProcessCases = getNotTaskCases(allProcessCases, processCaseIds);

    return mergeDataCountEntities(
        buildDataCountEntities(notTaskApiCases),
        buildDataCountEntities(notTaskProcessCases)
    );
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
   * 构建数据统计实体列表
   *
   * @param caseList
   * @return
   */
  @Override
  public List<DataCountEntity> buildDataCountEntities(List<?> caseList) {
    Map<String, Long> nameCountMap = caseList.stream()
        .collect(Collectors.groupingBy(
            this::getName,
            Collectors.counting()
        ));

    return nameCountMap.entrySet().stream()
        .map(entry -> new DataCountEntity(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  /**
   * 获取用例名称
   *
   * @param caseItem
   * @return
   */
  @Override
  public String getName(Object caseItem) {
    if (caseItem instanceof ApiCase) {
      Long userId = ((ApiCase) caseItem).getOwnerId();
      User user = userRepositoryService.getById(userId);
      return user.getName();
    } else if (caseItem instanceof ProcessCase) {
      Long userId = ((ProcessCase) caseItem).getUpdateById();
      User user = userRepositoryService.getById(userId);
      return user.getName();
    } else {
      throw new IllegalArgumentException(
          ResultEnum.INTERNAL_SERVER_ERROR.getMessage() + caseItem.getClass());
    }
  }

  /**
   * 合并数据统计实体列表
   *
   * @param apiCaseList
   * @param processCaseList
   * @return
   */
  @Override
  public List<DataCountEntity> mergeDataCountEntities(List<DataCountEntity> apiCaseList,
      List<DataCountEntity> processCaseList) {
    Map<String, Long> mergedMap = new HashMap<>();

    apiCaseList.forEach(entity -> mergedMap.merge(entity.getName(), entity.getCount(), Long::sum));

    processCaseList
        .forEach(entity -> mergedMap.merge(entity.getName(), entity.getCount(), Long::sum));

    return mergedMap.entrySet().stream()
        .map(entry -> new DataCountEntity(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
