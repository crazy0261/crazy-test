package com.example.crazytest.entity;


import com.example.crazytest.enums.NodeStatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:09
 * @DESRIPTION 结果信息
 */

@Data
public class ExecutionResult {

  private String nodeId;
  private String nextNodeId;
  private NodeStatusEnum status;
  private String message;
  private LocalDateTime timestamp;
  Map<String, Node> nodeMap;
  Map<String, List<Edge>> edgeMap;
  private String sqlExecResult;
  private String preStepExecResult;
  private String subProcessInputParams;
  private String outputParams;
  private Long subResultId;



}