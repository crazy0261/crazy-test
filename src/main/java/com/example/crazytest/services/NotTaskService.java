package com.example.crazytest.services;

import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.TaskSchedule;
import java.util.List;

public interface NotTaskService {

  List<DataCountEntity> notTaskCount();

  List<Long> extractCaseIds(List<TaskSchedule> taskSchedules);

  <T> List<T> getNotTaskCases(List<T> allCases, List<Long> taskCaseIds);

  Long getId(Object caseItem);

  List<DataCountEntity> buildDataCountEntities(List<?> caseList);

  String getName(Object caseItem);

  List<DataCountEntity> mergeDataCountEntities(List<DataCountEntity> apiCaseList,
      List<DataCountEntity> processCaseList);

}
