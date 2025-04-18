package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ProcessCaseNodeResultRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.services.ProcessCaseExecService;
import com.example.crazytest.services.SubProcessTaskService;
import com.example.crazytest.utils.JSONPathUtil;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/16 17:00
 * @DESRIPTION
 */

@Service
public class SubProcessTaskServiceImp implements SubProcessTaskService {

  @Autowired
  ProcessCaseNodeResultRepositoryService processCaseNodeResultRepositoryService;

  @Autowired
  ProcessCaseResultRepositoryService processCaseResultService;

  @Autowired
  ProcessCaseExecService processCaseExecService;

  /**
   * 处理子流程入参
   *
   * @param envParameter
   * @param inputParams
   */
  @Override
  public void handleInputParameters(Map<String, String> envParameter, String inputParams) {
    JSONObject inputJson = JSON.parseObject(inputParams);
    inputJson.forEach((key, value) -> envParameter
        .put(key, JSONPathUtil.isJsonPathValue(inputJson, value.toString())));
  }

  /**
   * 等待子任务完成
   *
   * @param result
   * @param envParameter
   * @param subResultId
   * @return
   */
  @Override
  public ExecutionResult waitForSubTaskCompletion(ExecutionResult result,
      Map<String, String> envParameter, Long subResultId) {

    long startTime = System.currentTimeMillis();
    while (processCaseExecService.isTimeout(startTime)) {
      ProcessCaseRecord processCaseRecord = processCaseResultService.getById(subResultId);

      if (isSubTaskFinished(processCaseRecord)) {
        handleSubTaskResult(result, envParameter, processCaseRecord, subResultId);
        return result;
      }
    }
    return setFailedResult(result, ResultEnum.NODE_EXEC_TIMEOUT.getMessage());
  }

  /**
   * 判断子任务是否完成
   *
   * @param processCaseRecord
   * @return
   */
  @Override
  public boolean isSubTaskFinished(ProcessCaseRecord processCaseRecord) {
    return Objects.nonNull(processCaseRecord) && Objects.nonNull(processCaseRecord.getStatus()) &&
        (Objects.equals(processCaseRecord.getStatus(), NodeStatusEnum.SUCCESS.name()) ||
            Objects.equals(processCaseRecord.getStatus(), NodeStatusEnum.FAILED.name()) ||
            Objects.equals(processCaseRecord.getStatus(), NodeStatusEnum.TIMEOUT.name()));
  }

  /**
   * 处理子任务结果
   *
   * @param result
   * @param envParameter
   * @param processCaseRecord
   * @param subResultId
   */
  @Override
  public void handleSubTaskResult(ExecutionResult result, Map<String, String> envParameter,
      ProcessCaseRecord processCaseRecord, Long subResultId) {
    if (Objects.equals(processCaseRecord.getStatus(), NodeStatusEnum.SUCCESS.name())) {
      ProcessCaseNodeResult processNodeResult = processCaseNodeResultRepositoryService.findLast(
          processCaseRecord.getProjectId(), subResultId);
      if (Objects.nonNull(processNodeResult)) {
        Map<String, String> params = JSON.parseObject(
            processNodeResult.getOutputParams(), new TypeReference<Map<String, String>>() {
            });
        envParameter.putAll(params);
        result.setStatus(NodeStatusEnum.SUCCESS);
        result.setMessage(JSON.toJSONString(params));
        return;
      }
    }
    result.setStatus(NodeStatusEnum.FAILED);
  }

  /**
   * 设置失败结果
   *
   * @param result
   * @param message
   * @return
   */
  @Override
  public ExecutionResult setFailedResult(ExecutionResult result, String message) {
    result.setStatus(NodeStatusEnum.FAILED);
    result.setMessage(message);
    return result;
  }
}
