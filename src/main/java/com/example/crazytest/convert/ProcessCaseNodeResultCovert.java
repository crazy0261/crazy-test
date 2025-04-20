package com.example.crazytest.convert;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.enums.NodeStatusEnum;
import java.util.ArrayList;
import java.util.Collections;

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

    processCaseRecord.setId(context.getResultId());
    processCaseRecord.setCaseId(processCase.getId());
    processCaseRecord.setAccountId(context.getApiDebugReq().getTestAccount());
    processCaseRecord.setProjectId(processCase.getProjectId());
    processCaseRecord.setAppId(processCase.getAppId());
    processCaseRecord.setStatus(NodeStatusEnum.PENDING.name());
    processCaseRecord.setScheduleId(context.getScheduleId());
    processCaseRecord.setScheduleBatchId(context.getScheduleBatchId());
    processCaseRecord.setNodes(mapArrayConvert.nodeMapToJsonArray(context.getNodeMap()).toString());
    processCaseRecord.setEdges(mapArrayConvert.edgeMapToJsonArray(context.getEdgeMap()).toString());
    processCaseRecord.setEnvSortId(context.getApiDebugReq().getEnvId());
    processCaseRecord.setMode(context.getApiDebugReq().getMode());
    return processCaseRecord;
  }

  public static ProcessCaseNodeResult processCaseNodeResultConvert(ExecutionResult executionResult,
      ExecutionProcessContext context) {
    ProcessCaseNodeResult processCaseNodeResult = new ProcessCaseNodeResult();
    processCaseNodeResult.setId(IdUtil.getSnowflakeNextId());
    processCaseNodeResult.setProjectId(context.getProjectId());
    processCaseNodeResult.setCaseId(context.getId());
    processCaseNodeResult.setCaseResultId(context.getResultId());
    processCaseNodeResult.setNodeId(context.getCurrentNode().getId());
    processCaseNodeResult.setNextNodeId(executionResult.getNextNodeId());
    processCaseNodeResult.setStatus(executionResult.getStatus().name());
    processCaseNodeResult.setDebugResult(executionResult.getMessage());
    processCaseNodeResult.setSqlExecResult(executionResult.getSqlExecResult());
    processCaseNodeResult.setPreStepExecResult(executionResult.getPreStepExecResult());
    processCaseNodeResult.setSubProcessInputParams(executionResult.getSubProcessInputParams());
    processCaseNodeResult.setOutputParams(executionResult.getOutputParams());
    processCaseNodeResult.setSubResultId(executionResult.getSubResultId());
    return processCaseNodeResult;
  }

}
