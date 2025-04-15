package com.example.crazytest.config;

import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
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
  private Long scheduleId;
  private Long scheduleBatchId;
  private String mode;
  Node currentNode;

  Map<String, Node> nodeMap;
  Map<String, List<Edge>> edgeMap;

  ApiDebugReq apiDebugReq;

  String token;
  Map<String, String> envParameter;


}
