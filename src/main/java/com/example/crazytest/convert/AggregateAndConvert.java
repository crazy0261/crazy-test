package com.example.crazytest.convert;

import com.example.crazytest.entity.AssetsNotEntity;
import com.example.crazytest.entity.User;
import com.example.crazytest.repository.UserRepositoryService;
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
public class AggregateAndConvert {

  @Autowired
  UserRepositoryService userRepository;

  /**
   * 合并两个Map，并转换为AssetsNotEntity列表
   * @param apiCaseAssetsCount
   * @param processCaseCount
   * @return
   */
  public List<AssetsNotEntity> aggregateAndConvert(Map<Long, Integer> apiCaseAssetsCount,
      Map<Long, Integer> processCaseCount) {

    Map<Long, Integer> mergedMap = new HashMap<>(apiCaseAssetsCount);
    processCaseCount.forEach((key, value) -> mergedMap.merge(key, value, Integer::sum));
    return mergedMap.entrySet().stream()
        .map(entry -> {
          Long id = entry.getKey();
          User user = userRepository.getById(id);
          return new AssetsNotEntity(user.getName(), entry.getValue());
        })
        .collect(Collectors.toList());
  }

}
