package com.example.crazytest.config;

import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.req.ApiDebugReq;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/15 13:30
 * @DESRIPTION
 */

@Data
public class ExecutionProcessContext {

  Long id;
  Long resultId;
  Long projectId;
  Node currentNode;
  ApiDebugReq apiDebugReq;
  ProcessCase processCase;
  private Long scheduleId;
  private Long scheduleBatchId;
  String conditionOutKey;
  Map<String, Node> nodeMap;
  Map<String, List<Edge>> edgeMap;
  Map<String, String> envParameter;
}
