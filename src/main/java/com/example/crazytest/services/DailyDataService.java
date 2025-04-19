package com.example.crazytest.services;

import com.example.crazytest.entity.UserDistributionEntity;
import com.example.crazytest.vo.CoreIndicatorsListVO;
import com.example.crazytest.vo.DailyDataCaseVO;
import java.time.LocalDate;
import java.util.List;

public interface DailyDataService {

  CoreIndicatorsListVO getCoreIndicatorsDetail();

  DailyDataCaseVO getTrendData(LocalDate startTime, LocalDate endTime);

  List<UserDistributionEntity> getUserDistribution();

  void createDataDaily(Long projectId);

}
