package com.example.crazytest.services;

import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.ProcessCaseRecord;
import java.util.Map;

public interface SubProcessTaskService {

  void handleInputParameters(Map<String, String> envParameter, String inputParams);

  ExecutionResult waitForSubTaskCompletion(ExecutionResult result, Map<String, String> envParameter, Long subResultId);

  boolean isSubTaskFinished(ProcessCaseRecord processCaseRecord);

  void handleSubTaskResult(ExecutionResult result, Map<String, String> envParameter, ProcessCaseRecord processCaseRecord, Long subResultId) ;

  ExecutionResult setFailedResult(ExecutionResult result, String message);


}
