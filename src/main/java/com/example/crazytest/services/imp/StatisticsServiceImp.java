package com.example.crazytest.services.imp;

import cn.hutool.core.convert.Convert;
import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.entity.NotTaskEntity;
import com.example.crazytest.entity.User;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.NotTaskService;
import com.example.crazytest.services.ProcessCaseNodeService;
import com.example.crazytest.services.StatisticsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/21 12:31
 * @DESRIPTION
 */

@Service
public class StatisticsServiceImp implements StatisticsService {

  @Autowired
  UserRepositoryService userRepository;


  @Autowired
  ApiCaseService apiCaseService;

  @Autowired
  ProcessCaseNodeService processNodeService;


  @Autowired
  NotTaskService notTaskService;

  /**
   * 将它们合并成一个map，并按照value值从大到小排序，取前5个，
   *
   * @return
   */
  @Override
  public List<DataCountEntity> assetsAndCount() {

    Map<Long, Integer> apiCaseAssetsCount = apiCaseService.getAssetsNot();
    Map<Long, Integer> processCaseCount = processNodeService.getAssetsNotCount();

    Map<Long, Integer> mergedMap = new HashMap<>(apiCaseAssetsCount);
    processCaseCount.forEach((key, value) -> mergedMap.merge(key, value, Integer::sum));
    return mergedMap.entrySet().stream()
        .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
        .limit(5)
        .map(entry -> {
          Long id = entry.getKey();
          User user = userRepository.getById(id);
          return new DataCountEntity(user.getName(), Convert.toLong(entry.getValue()));
        })
        .collect(Collectors.toList());
  }

  /**
   * 将未断言它们合并成一个list
   *
   * @return
   */
  @Override
  public List<AssetsNotListEntity> assetsList() {
    List<AssetsNotListEntity> apiCaseList = apiCaseService.getAssetsNotMap();
    List<AssetsNotListEntity> processCaseList = processNodeService.getAssetsNotMap();
    apiCaseList.addAll(processCaseList);
    return apiCaseList;
  }

  /**
   * 未添加任务用例-用户分布
   *
   * @return
   */
  @Override
  public List<DataCountEntity> notTaskCount() {
    return notTaskService.notTaskCount();
  }

  /**
   * 未添加任务用例-用例
   *
   * @return
   */
  @Override
  public List<NotTaskEntity> notTaskList() {

    return null;
  }

  /**
   * 用例失败人员分布
   *
   * @return
   */
  @Override
  public List<DataCountEntity> failCaseCount() {
    return null;
  }

  /**
   * 用例失败明细
   *
   * @return
   */
  @Override
  public List<NotFailEntity> failCaseList() {
    return null;
  }


}
