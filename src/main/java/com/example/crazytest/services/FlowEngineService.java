package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;

public interface FlowEngineService {

  void executeFlow(String nodesJson, String edgesJson, ExecutionProcessContext context);
}
