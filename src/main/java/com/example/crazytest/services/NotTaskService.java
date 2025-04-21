package com.example.crazytest.services;

import com.example.crazytest.dto.TaskDailyDTO;
import com.example.crazytest.entity.DataTaskCountEntity;
import com.example.crazytest.entity.NotTaskEntity;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.vo.StatisticsDetailVO;
import java.util.List;

public interface NotTaskService {

  StatisticsDetailVO getDailyTask();

  List<DataTaskCountEntity> notTaskCount(TaskDailyDTO taskDaily);

  List<NotTaskEntity> notTaskList(TaskDailyDTO taskDaily);

  TaskDailyDTO taskDailyService();

  List<Long> extractCaseIds(List<TaskSchedule> taskSchedules);

  <T> List<T> getNotTaskCases(List<T> allCases, List<Long> taskCaseIds);

  Long getId(Object caseItem);

  List<DataTaskCountEntity> buildDataCountEntities(List<?> caseList);

  String getAppName(Object caseItem);

  List<DataTaskCountEntity> mergeDataCountTaskEntities(List<DataTaskCountEntity> apiCaseList,
      List<DataTaskCountEntity> processCaseList);

}
