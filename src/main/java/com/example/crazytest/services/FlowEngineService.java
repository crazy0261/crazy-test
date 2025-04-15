package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.Node;
import java.util.List;

public interface FlowEngineService {

  void executeFlow(String nodesJson, String edgesJson, ExecutionProcessContext context);

  void handleGlobalTimeout(List<Node> nodes);
}
