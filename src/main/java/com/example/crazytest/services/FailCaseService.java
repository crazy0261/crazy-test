package com.example.crazytest.services;

import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.vo.StatisticsDetailVO;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface FailCaseService {

  StatisticsDetailVO failCase();

  Set<Long> getContinuousFailedIds(List<?> records);

  boolean isFailed(Object recordObj);

  LocalDate getDate(Object recordObj);

  Long getId(Object recordObj);

  List<NotFailEntity> failCaseList(List<ApiCase> apiCaseList, List<ProcessCase> processCaseList);

  List<DataCountEntity> buildDataCountEntities(List<?> caseList);

  List<DataCountEntity> mergeDataCountEntities(List<DataCountEntity> apiCaseList,
      List<DataCountEntity> processCaseList);

  String getName(Object caseItem);

}
