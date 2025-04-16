package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.crazytest.services.ProcessCaseExecService;
import com.example.crazytest.utils.CommonUtil;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/16 22:23
 * @DESRIPTION
 */

@Service
public class ProcessCaseExecServiceImp implements ProcessCaseExecService {


  /**
   * 判断是否超时
   *
   * @param startTime
   * @return
   */
  @Override
  public boolean isTimeout(long startTime) {
    return System.currentTimeMillis() - startTime < CommonUtil.NODE_TIMEOUT_MINUTES;
  }

  /**
   * 获取子环境id
   *
   * @param isSubEnv
   * @param envId
   * @return
   */
  @Override
  public Long getSubEnvId(String isSubEnv, Long envId) {
    return Optional.ofNullable(isSubEnv)
        .map(JSON::parseArray)
        .map(JSONArray::stream)
        .flatMap(stream -> stream
            .map(json -> JSON.parseObject(json.toString()))
            .filter(item -> Objects.equals(item.getLong("envId"), envId))
            .findFirst())
        .map(item -> item.getLong("subEnvId"))
        .orElse(null);
  }
}
