package com.example.crazytest.services;

public interface ProcessCaseExecService {

  boolean isTimeout(long startTime);

  Long getSubEnvId(String isSubEnv, Long envId);

}
