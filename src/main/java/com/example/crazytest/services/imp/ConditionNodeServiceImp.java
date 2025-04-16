package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.utils.GroovyExecUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:28
 * @DESRIPTION
 */

@Service
public class ConditionNodeServiceImp implements NodeService {

  @Autowired
  ProcessCaseNodeRepositoryService processCaseNodeRepositoryService;

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ExecutionResult result = new ExecutionResult();
    Map<String, String> envParameter = context.getEnvParameter();
    ProcessCaseNode processCaseNode = processCaseNodeRepositoryService
        .detail(context.getProjectId(), Long.valueOf(node.getId()));

    try {
      Object groovyValue = GroovyExecUtil
          .executeGroovy(processCaseNode.getGroovyScript(), envParameter);
      envParameter.put(processCaseNode.getGroovyKey(), groovyValue.toString());
      result.setStatus(NodeStatusEnum.SUCCESS);
      result.setMessage(groovyValue.toString());

      context.setEnvParameter(envParameter);
    } catch (Exception e) {
      result.setStatus(NodeStatusEnum.FAILED);
      result.setMessage(e.getMessage());
    }
    return result;
  }
}
