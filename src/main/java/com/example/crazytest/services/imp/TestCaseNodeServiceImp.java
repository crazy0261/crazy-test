package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.CaseDebugService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.utils.JSONPathUtil;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:27
 * @DESRIPTION
 */

@Service
public class TestCaseNodeServiceImp implements NodeService {

  @Autowired
  ProcessCaseNodeRepositoryService processCaseNodeRepositoryService;

  @Autowired
  CaseDebugService casesDebugService;

  @Override
  public NodeTypeEnum getSupportedType() {
    return null;
  }

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) throws IOException {
    ExecutionResult result = new ExecutionResult();
    ProcessCaseNode processCaseNode = processCaseNodeRepositoryService
        .detail(context.getProjectId(), Long.valueOf(node.getId()));
    ResultApiVO resultApiVO = casesDebugService.processApiCaseDebug(processCaseNode, context);

    JSONObject outParamJson = JSON.parseObject(processCaseNode.getOutputParams());

    Map<String, String> envParameter = context.getEnvParameter();
    Map<String, String> outParamMap = new HashMap<>();
    outParamJson.forEach((key, value) -> {
      if (JSONPathUtil.isJsonPathCheck(value.toString())) {
        outParamMap.put(key, Objects.requireNonNull(
            JSONPathUtil.getJsonPathValue(outParamJson, value.toString())).toString());
      }
    });

    envParameter.putAll(outParamMap);
    context.setEnvParameter(envParameter);

    result.setStatus(
        Boolean.TRUE.equals(resultApiVO.getAssertResultVo().getPass()) ? NodeStatusEnum.SUCCESS
            : NodeStatusEnum.FAILED);
    result.setMessage(resultApiVO.getResponse().toJSONString());
    return result;
  }
}
