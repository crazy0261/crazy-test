package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import java.util.List;
import java.util.Map;

public interface FlowExecutorService {

  void execute(List<Node> nodes, List<Edge> edges, ExecutionProcessContext context);

  ExecutionResult executeNode(Node node, ExecutionProcessContext context);

  void currentNodeRunning(Long id,Map<String, Node> nodeMap, String currentNode);

  Node findNextNode(Node currentNode, Map<String, List<Edge>> edgeMap, Map<String, Node> nodeMap,ExecutionProcessContext context);

  void markRemainingAsFailed(Map<String, Node> nodeMap, String type);

  Node findStartNode(List<Node> nodes);

  void handleTimeout(long resultId, Map<String, Node> nodeMap, ExecutionProcessContext context);

  void handleFailedNode(long resultId, Map<String, Node> nodeMap, ExecutionResult result, ExecutionProcessContext context);

  void handleSuccessfulNode(long resultId, Map<String, Node> nodeMap, ExecutionResult result, ExecutionProcessContext context);


  void saveNodeResult(ExecutionResult result, ExecutionProcessContext context);
}
