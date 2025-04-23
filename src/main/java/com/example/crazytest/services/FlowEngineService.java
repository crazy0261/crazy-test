package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ProcessCase;

public interface FlowEngineService {

  void executeFlow(ProcessCase processCase, ExecutionProcessContext context);
}
