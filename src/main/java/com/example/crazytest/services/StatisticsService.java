package com.example.crazytest.services;

import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.vo.StatisticsDetailVO;
import java.util.List;

public interface StatisticsService {

  List<DataCountEntity> assetsAndCount();

  List<AssetsNotListEntity> assetsList();

  StatisticsDetailVO notTaskCase();

  StatisticsDetailVO failCase();


}
