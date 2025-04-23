package com.example.crazytest.services.imp;

import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 22:03
 * @DESRIPTION
 */


@Service
public class FlowValidatorImp {

  public void validate(List<Node> nodes, List<Edge> edges) {
    if (nodes == null || nodes.isEmpty()) {
      throw new IllegalArgumentException("节点列表不能为空");
    }

    Set<String> nodeIds = nodes.stream().map(Node::getId).collect(Collectors.toSet());
    edges.forEach(edge -> {
      if (!nodeIds.contains(edge.getSource())) {
        throw new IllegalArgumentException("无效的源节点: " + edge.getSource());
      }
      if (!nodeIds.contains(edge.getTarget())) {
        throw new IllegalArgumentException("无效的目标节点: " + edge.getTarget());
      }
    });
  }
}
