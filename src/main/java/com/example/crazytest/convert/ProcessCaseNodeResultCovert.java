package com.example.crazytest.convert;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.entity.ProcessCaseResult;
import com.example.crazytest.enums.NodeStatusEnum;

/**
 * @author
 * @name Menghui
 * @date 2025/4/15 14:55
 * @DESRIPTION
 */


public class ProcessCaseNodeResultCovert {

  private ProcessCaseNodeResultCovert() {
  }


  public static ProcessCaseResult processCaseResultConvert(ProcessCase processCase,
      ExecutionProcessContext context) {
    ProcessCaseResult processCaseResult = new ProcessCaseResult();
    processCaseResult.setId(IdUtil.getSnowflakeNextId());
    processCaseResult.setCaseId(processCase.getId());
    processCaseResult.setProjectId(processCase.getProjectId());
    processCaseResult.setAppId(processCase.getAppId());
    processCaseResult.setStatus(NodeStatusEnum.PENDING.name());
    processCaseResult.setScheduleId(context.getScheduleId());
    processCaseResult.setScheduleBatchId(context.getScheduleBatchId());
    processCaseResult.setNodes(JSON.toJSONString(context.getNodeMap()));
    processCaseResult.setEdges(JSON.toJSONString(context.getEdgeMap()));
    processCaseResult.setEnvNameId(context.getApiDebugReq().getEnvId());
    processCaseResult.setMode(context.getMode());
    return processCaseResult;
  }

  public static ProcessCaseNodeResult processCaseNodeResultConvert(ExecutionResult executionResult,
      ExecutionProcessContext context) {
    ProcessCaseNodeResult processCaseNodeResult = new ProcessCaseNodeResult();
    processCaseNodeResult.setId(IdUtil.getSnowflakeNextId());
    processCaseNodeResult.setCaseId(context.getId());
    processCaseNodeResult.setNodeId(executionResult.getNodeId());
    processCaseNodeResult.setNextNodeId(executionResult.getNextNodeId());
    processCaseNodeResult.setDebugResult(executionResult.getMessage());
    processCaseNodeResult.setSqlExecResult(executionResult.getSqlExecResult());
    processCaseNodeResult.setPreStepExecResult(executionResult.getPreStepExecResult());
    processCaseNodeResult.setSubProcessInputParams(executionResult.getSubProcessInputParams());
    processCaseNodeResult.setOutputParams(executionResult.getOutputParams());
    processCaseNodeResult.setSubResultId(executionResult.getSubResultId());
    return processCaseNodeResult;
  }

}
