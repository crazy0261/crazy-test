package com.example.crazytest.convert;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.entity.ProcessCaseRecord;
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


  public static ProcessCaseRecord processCaseResultConvert(ProcessCase processCase,
      ExecutionProcessContext context) {
    ProcessCaseRecord processCaseRecord = new ProcessCaseRecord();
    processCaseRecord.setId(IdUtil.getSnowflakeNextId());
    processCaseRecord.setCaseId(processCase.getId());
    processCaseRecord.setProjectId(processCase.getProjectId());
    processCaseRecord.setAppId(processCase.getAppId());
    processCaseRecord.setStatus(NodeStatusEnum.PENDING.name());
    processCaseRecord.setScheduleId(context.getScheduleId());
    processCaseRecord.setScheduleBatchId(context.getScheduleBatchId());
    processCaseRecord.setNodes(JSON.toJSONString(context.getNodeMap()));
    processCaseRecord.setEdges(JSON.toJSONString(context.getEdgeMap()));
    processCaseRecord.setEnvNameId(context.getApiDebugReq().getEnvId());
    processCaseRecord.setMode(context.getMode());
    return processCaseRecord;
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
