package com.example.crazytest.services;

import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCaseResult;
import java.util.Map;

public interface SubProcessTaskService {

  void handleInputParameters(Map<String, String> envParameter, String inputParams);

  ExecutionResult waitForSubTaskCompletion(ExecutionResult result, Map<String, String> envParameter, Long subResultId);

  boolean isSubTaskFinished(ProcessCaseResult processCaseResult);

  void handleSubTaskResult(ExecutionResult result, Map<String, String> envParameter, ProcessCaseResult processCaseResult, Long subResultId) ;

  ExecutionResult setFailedResult(ExecutionResult result, String message);


}
