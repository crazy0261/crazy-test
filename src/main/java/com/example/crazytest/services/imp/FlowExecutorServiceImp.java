package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.convert.ProcessCaseNodeResultCovert;
import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.factory.NodeServiceFactory;
import com.example.crazytest.repository.ProcessCaseNodeResultRepositoryService;
import com.example.crazytest.services.FlowExecutorService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.services.ProcessCaseExecService;
import com.example.crazytest.services.ProcessCaseResultService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:18
 * @DESRIPTION
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowExecutorServiceImp implements FlowExecutorService {

  private final NodeServiceFactory serviceFactory;

  @Autowired
  ProcessCaseResultService processCaseResultService;

  @Autowired
  ProcessCaseNodeResultRepositoryService processCaseNodeResultRepositoryService;

  @Autowired
  ProcessCaseExecService processCaseExecService;

  /**
   * 执行流程
   *
   * @param nodes
   * @param edges
   * @param context
   */
  @Override
  public void execute(List<Node> nodes, List<Edge> edges, ExecutionProcessContext context) {
    Map<String, Node> nodeMap = nodes.stream()
        .collect(Collectors.toMap(Node::getId, Function.identity()));
    Map<String, List<Edge>> edgeMap = edges.stream()
        .collect(Collectors.groupingBy(Edge::getSource));

    context.setNodeMap(nodeMap);
    context.setEdgeMap(edgeMap);

    processCaseResultService.save(context.getProcessCase(), context);

    long startTime = System.currentTimeMillis();
    Node currentNode = findStartNode(nodes);

    while (currentNode != null) {
      context.setCurrentNode(currentNode);

      if (processCaseExecService.isTimeout(startTime)) {
        handleTimeout(context.getResultId(), nodeMap, context);
        break;
      }

      ExecutionResult result = executeNode(currentNode, context);

      if (Objects.equals(result.getStatus(), NodeStatusEnum.FAILED)) {
        handleFailedNode(context.getResultId(), nodeMap, result, context);
      }else {
        handleSuccessfulNode(context.getResultId(), nodeMap,result, context);
      }


      currentNode = findNextNode(currentNode, edgeMap, nodeMap,context);
      String nextNodeId = Optional.ofNullable(currentNode).map(Node::getId).orElse("");
      result.setNextNodeId(nextNodeId);

      processCaseResultService.updateNodes(context.getResultId(), nodeMap,
          StringUtils.isEmpty(nextNodeId)? NodeStatusEnum.SUCCESS.name(): NodeStatusEnum.RUNNING.name());
      saveNodeResult(result, context);
    }
  }

  /**
   * 执行节点
   *
   * @param node
   * @param context
   * @return
   */
  @Override
  public ExecutionResult executeNode(Node node, ExecutionProcessContext context) {
    ExecutionResult executionResult = new ExecutionResult();
    currentNodeRunning(context.getResultId(), context.getNodeMap(), node.getId());

    NodeService service = serviceFactory.getService(node.getType());

    try {
      return service.execute(node, context);
    } catch (Exception e) {
      node.updateStatus(NodeStatusEnum.FAILED);
      executionResult.setStatus(NodeStatusEnum.FAILED);
      return executionResult;
    }
  }

  @Override
  public void currentNodeRunning(Long id, Map<String, Node> nodeMap, String currentNode) {
    if (Objects.nonNull(currentNode)) {
      nodeMap.get(currentNode).getData()
          .setBorderColor(NodeStatusEnum.RUNNING.getColor());
      processCaseResultService.updateNodes(id, nodeMap, NodeStatusEnum.RUNNING.name());
    }
  }

  /**
   * 查找下一个节点
   *
   * @param currentNode
   * @param edgeMap
   * @param nodeMap
   * @return
   */
  @Override
  public Node findNextNode(Node currentNode, Map<String, List<Edge>> edgeMap,
      Map<String, Node> nodeMap,ExecutionProcessContext context) {
    return edgeMap.getOrDefault(currentNode.getId(), Collections.emptyList()).stream()
        .filter(edge -> {
          if (Objects.equals(edge.getLabel(),context.getConditionOutKey())){
            return true;
          }
          return Objects.isNull(edge.getLabel()) ;
        })
        .findFirst()
        .map(Edge::getTarget)
        .map(nodeMap::get)
        .orElse(null);
  }

  /**
   * 标记剩余节点为失败
   *
   * @param nodeMap
   * @param type
   */

  @Override
  public void markRemainingAsFailed(Map<String, Node> nodeMap, String type) {
    nodeMap.values().stream().filter(node -> Objects.isNull(node.getData().getColor()))
        .forEach(node -> node.getData().setColor(NodeStatusEnum.getValueByType(type)));
  }

  /**
   * 查找起始节点或者第一个节点
   *
   * @param nodes
   * @return
   */
  @Override
  public Node findStartNode(List<Node> nodes) {
    return nodes.stream()
        .filter(n -> n.getType().equals(NodeTypeEnum.START_NODE.getTypeName()))
        .findFirst()
        .orElse(null);
  }

  /**
   * 超时处理
   *
   * @param resultId
   * @param nodeMap
   * @param context
   */
  @Override
  public void handleTimeout(long resultId, Map<String, Node> nodeMap,
      ExecutionProcessContext context) {
    markRemainingAsFailed(nodeMap, NodeStatusEnum.TIMEOUT.name());
    processCaseResultService.updateNodes(resultId, nodeMap, NodeStatusEnum.TIMEOUT.name());
  }

  /**
   * 失败节点处理
   *
   * @param resultId
   * @param nodeMap
   * @param result
   * @param context
   */
  @Override
  public void handleFailedNode(long resultId, Map<String, Node> nodeMap, ExecutionResult result,
      ExecutionProcessContext context) {
    markRemainingAsFailed(nodeMap, NodeStatusEnum.FAILED.name());
    result.setNodeMap(nodeMap);
    ProcessCaseNodeResult processCaseNodeResult = ProcessCaseNodeResultCovert
        .processCaseNodeResultConvert(result, context);
    processCaseResultService.updateNodes(resultId, nodeMap, NodeStatusEnum.FAILED.name());
    processCaseNodeResultRepositoryService.saveOrUpdate(processCaseNodeResult);
  }

  @Override
  public void handleSuccessfulNode(long resultId, Map<String, Node> nodeMap, ExecutionResult result,
      ExecutionProcessContext context) {
    markRemainingAsFailed(nodeMap, NodeStatusEnum.SUCCESS.name());
    result.setNodeMap(nodeMap);
  }

  /**
   * 保存节点结果
   *
   * @param result
   * @param context
   */
  @Override
  public void saveNodeResult(ExecutionResult result, ExecutionProcessContext context) {
    ProcessCaseNodeResult processCaseNodeResult = ProcessCaseNodeResultCovert
        .processCaseNodeResultConvert(result, context);
    processCaseNodeResultRepositoryService.saveOrUpdate(processCaseNodeResult);
  }
}
