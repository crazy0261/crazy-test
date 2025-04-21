package com.example.crazytest.services;

import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.entity.NotTaskEntity;
import java.util.List;

public interface StatisticsService {

  List<DataCountEntity> assetsAndCount();

  List<AssetsNotListEntity> assetsList();

  List<DataCountEntity> notTaskCount();

  List<NotTaskEntity> notTaskList();

  List<DataCountEntity> failCaseCount();

  List<NotFailEntity> failCaseList();

}
