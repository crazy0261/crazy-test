package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.services.ProcessCaseExecService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.services.SubProcessTaskService;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:26
 * @DESRIPTION
 */

@Service
public class SubProcessNodeServiceImp implements NodeService {

  @Autowired
  SubProcessTaskService subProcessTaskService;

  @Autowired
  ProcessCaseNodeRepositoryService processCaseNodeRepositoryService;

  @Autowired
  ProcessCaseExecService processCaseExecService;

  @Autowired
  @Lazy
  ProcessCaseService processCaseService;

  @Override
  public NodeTypeEnum getSupportedType() {
    return null;
  }

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ExecutionResult result = new ExecutionResult();
    Map<String, String> envParameter = context.getEnvParameter();
    ProcessCaseNode processCaseNode = processCaseNodeRepositoryService.detail(context.getProjectId(), Long.valueOf(node.getId()));

    subProcessTaskService.handleInputParameters(envParameter, processCaseNode.getInputParams());

    // 获取sub envId
    Long subEnvId =processCaseExecService.getSubEnvId(processCaseNode.getIsSubEnv(), context.getApiDebugReq().getEnvId());

    if (Objects.isNull(subEnvId)) {
      return subProcessTaskService.setFailedResult(result,ResultEnum.SUB_ENV_NOT_EXIST.getMessage());
    }

      Long subResultId = processCaseService.executeSubTask(envParameter,processCaseNode.getSubCaseId(),subEnvId);

      if (Objects.isNull(subResultId)){
        return subProcessTaskService.setFailedResult(result,ResultEnum.SUB_ENV_NOT_EXIST.getMessage());
      }

      return  subProcessTaskService.waitForSubTaskCompletion(result,envParameter,subResultId);
  }
}
