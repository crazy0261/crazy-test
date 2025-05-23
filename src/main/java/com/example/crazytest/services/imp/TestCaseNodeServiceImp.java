package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.CaseDebugService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.utils.JSONPathUtil;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
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
  public ExecutionResult execute(Node node, ExecutionProcessContext context) throws IOException {
    ExecutionResult result = new ExecutionResult();
    ProcessCaseNode processCaseNode = processCaseNodeRepositoryService
        .detail(context.getProjectId(), Long.valueOf(node.getId()));
    ResultApiVO resultApiVO = casesDebugService.processApiCaseDebug(processCaseNode, context);

    JSONObject outParamJson = JSON.parseObject(processCaseNode.getOutputParams());

    Map<String, String> envParameter = context.getEnvParameter();
    outParamJson.forEach((key, value) -> envParameter
        .put(key, JSONPathUtil.isJsonPathValue(outParamJson, value.toString())));
    context.setEnvParameter(envParameter);

    result.setStatus(
        Optional.ofNullable(resultApiVO.getAssertResultVo()).map(AssertResultVo::getPass)
            .filter(Boolean.TRUE::equals).map(pass -> ExecStatusEnum.SUCCESS)
            .orElse(ExecStatusEnum.FAILED));
    result.setMessage(JSON.toJSONString(resultApiVO));
    return result;
  }
}
