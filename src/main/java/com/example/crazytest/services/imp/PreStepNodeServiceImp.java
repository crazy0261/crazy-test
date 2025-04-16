package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.services.NodeService;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:28
 * @DESRIPTION 前置条件实现
 */

@Service
public class PreStepNodeServiceImp implements NodeService {

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ExecutionResult executionResult = new ExecutionResult();
    Map<String, String> env = context.getEnvParameter();
    ProcessCase processCaseNode = processCaseRepositoryService
        .getById(Long.parseLong(node.getId()));

    Map<String, String> envParameter = Optional.ofNullable(processCaseNode)
        .map(ProcessCase::getInputParams)
        .map(json -> JSON.parseObject(json, new TypeReference<Map<String, JSONObject>>() {
        }))
        .map(item -> item.get(String.valueOf(context.getApiDebugReq().getEnvId())))
        .map(envItem ->
            envItem.getJSONArray("params").stream()
                .map(json -> JSON.parseObject(json.toString(), JSONObject.class))
                .collect(Collectors
                    .toMap(param -> param.getString("key"), param -> param.getString("value"),
                        (a, b) -> b)
                )
        ).orElseGet(Collections::emptyMap);

    env.putAll(envParameter);
    context.setEnvParameter(env);

    executionResult.setPreStepExecResult(JSON.toJSONString(envParameter));
    executionResult.setStatus(NodeStatusEnum.SUCCESS);
    return executionResult;
  }
}
