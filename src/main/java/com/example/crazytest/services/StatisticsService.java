package com.example.crazytest.services;

import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.vo.StatisticsDetailVO;
import java.util.List;

public interface StatisticsService {

  List<DataCountEntity> assetsAndCount();

  List<AssetsNotListEntity> assetsList();

  StatisticsDetailVO notTaskCase();

  List<DataCountEntity> failCaseCount();

  List<NotFailEntity> failCaseList();

}
