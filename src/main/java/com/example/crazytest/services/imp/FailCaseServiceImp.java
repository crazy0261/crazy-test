package com.example.crazytest.services.imp;

import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.entity.User;
import com.example.crazytest.enums.CaseTypeEnums;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.FailCaseService;
import com.example.crazytest.services.NotTaskService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.StatisticsDetailVO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/21 18:51
 * @DESRIPTION
 */

@Service
public class FailCaseServiceImp implements FailCaseService {

  @Autowired
  ApiCaseResultRepositoryService apiCaseResultRepositoryService;

  @Autowired
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Autowired
  ProcessCaseResultRepositoryService processCaseResultRepositoryService;

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  NotTaskService notTaskService;

  @Autowired
  UserRepositoryService userRepositoryService;


  /**
   * 失败统计
   *
   * @return
   */
  @Override
  public StatisticsDetailVO failCase() {
    StatisticsDetailVO statisticsDetailVO = new StatisticsDetailVO();
    Long projectId = BaseContext.getSelectProjectId();
    LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3).with(LocalTime.MIN);

    List<ApiCaseRecord> apiCaseRecordList = apiCaseResultRepositoryService
        .getApiCaseFailed(projectId, threeDaysAgo);
    List<ProcessCaseRecord> processCaseRecordList = processCaseResultRepositoryService
        .getProcessCaseRecordFailList(projectId, threeDaysAgo);

    Set<Long> apiCaseFailedIds = this.getContinuousFailedIds(apiCaseRecordList);
    Set<Long> processCaseFailedIds = this.getContinuousFailedIds(processCaseRecordList);

    List<ApiCase> apiCaseList = Optional.ofNullable(apiCaseFailedIds)
        .filter(ids -> !ids.isEmpty())
        .map(ArrayList::new)
        .map(apiCaseRepositoryService::queryIds)
        .orElseGet(ArrayList::new);
    List<ProcessCase> processCaseList = Optional.ofNullable(processCaseFailedIds)
        .filter(ids -> !ids.isEmpty())
        .map(ArrayList::new)
        .map(processCaseRepositoryService::getIdsList)
        .orElseGet(ArrayList::new);

    List<DataCountEntity> apiCaseListData = this.buildDataCountEntities(apiCaseList);
    List<DataCountEntity> processCaseListData = this.buildDataCountEntities(processCaseList);

    List<DataCountEntity> dataCountEntities = this
        .mergeDataCountEntities(apiCaseListData, processCaseListData);
    List<NotFailEntity> failCaseList = this.failCaseList(apiCaseList, processCaseList);

    statisticsDetailVO.setFailCaseCount(dataCountEntities);
    statisticsDetailVO.setFailCaseList(failCaseList);
    return statisticsDetailVO;
  }

  /**
   * 获取连续失败的用例id
   *
   * @param records
   * @return
   */
  @Override
  public Set<Long> getContinuousFailedIds(List<?> records) {

    Map<LocalDate, Object> recentRecords = records.stream()
        .collect(Collectors.toMap(this::getDate, Function.<Object>identity(), (a, b) -> b));

    boolean isContinuousFailed = Stream.of(
        recentRecords.getOrDefault(LocalDate.now().minusDays(2), null),
        recentRecords.getOrDefault(LocalDate.now().minusDays(1), null),
        recentRecords.getOrDefault(LocalDate.now(), null)
    )
        .filter(Objects::nonNull)
        .allMatch(this::isFailed);
    return isContinuousFailed ? records.stream()
        .map(this::getId)
        .collect(Collectors.toSet()) : Collections.emptySet();
  }

  /**
   * 判断是否失败
   *
   * @param recordObj
   * @return
   */
  @Override
  public boolean isFailed(Object recordObj) {
    if (recordObj instanceof ApiCaseRecord) {
      String statusStatus = ((ApiCaseRecord) recordObj).getStatus();
      return ExecStatusEnum.FAILED.name().equals(statusStatus) || ExecStatusEnum.TIMEOUT
          .name().equals(statusStatus);
    } else if (recordObj instanceof ProcessCaseRecord) {
      String statusStatus = ((ProcessCaseRecord) recordObj).getStatus();
      return ExecStatusEnum.FAILED.name().equals(statusStatus) || ExecStatusEnum.TIMEOUT
          .name().equals(statusStatus);
    }
    return Boolean.FALSE;
  }

  /**
   * 获取日期
   *
   * @param recordObj
   * @return
   */
  @Override
  public LocalDate getDate(Object recordObj) {
    if (recordObj instanceof ApiCaseRecord) {
      return ((ApiCaseRecord) recordObj).getCreateTime().toLocalDate();
    } else if (recordObj instanceof ProcessCaseRecord) {
      return ((ProcessCaseRecord) recordObj).getCreateTime().toLocalDate();
    }
    return null;
  }

  /**
   * 获取用例id
   *
   * @param recordObj
   * @return
   */
  public Long getId(Object recordObj) {
    if (recordObj instanceof ApiCaseRecord) {
      return ((ApiCaseRecord) recordObj).getApiTestcaseId();
    } else if (recordObj instanceof ProcessCaseRecord) {
      return ((ProcessCaseRecord) recordObj).getCaseId();
    }
    return null;
  }

  /**
   * 失败用例列表
   *
   * @param apiCaseList
   * @param processCaseList
   * @return
   */
  @Override
  public List<NotFailEntity> failCaseList(List<ApiCase> apiCaseList,
      List<ProcessCase> processCaseList) {
    List<NotFailEntity> failedArray = new ArrayList<>();
    apiCaseList.forEach(apiCase -> {
      NotFailEntity failedEntity = new NotFailEntity();
      User user = userRepositoryService.getById(apiCase.getOwnerId());
      failedEntity.setId(apiCase.getId());
      failedEntity.setName(apiCase.getName());
      failedEntity.setType(CaseTypeEnums.API_CASE_TYPE.getType());
      failedEntity.setOwnerName(user.getName());
      failedArray.add(failedEntity);
    });

    processCaseList.forEach(processCase -> {
      NotFailEntity failedEntity = new NotFailEntity();
      User user = userRepositoryService.getById(processCase.getOwnerId());
      failedEntity.setId(processCase.getId());
      failedEntity.setName(processCase.getName());
      failedEntity.setType(CaseTypeEnums.PROCESS_CASE_TYPE.getType());
      failedEntity.setOwnerName(user.getName());
      failedArray.add(failedEntity);
    });
    return failedArray;
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
   * 合并数据统计实体列表,取倒叙10条
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
        .sorted(Comparator.comparing(DataCountEntity::getCount).reversed())
        .limit(10)
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
}
