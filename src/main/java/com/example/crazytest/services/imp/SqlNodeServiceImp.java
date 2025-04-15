package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.services.NodeService;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:29
 * @DESRIPTION sql 节点实现
 */

@Service
public class SqlNodeServiceImp implements NodeService {

  @Override
  public NodeTypeEnum getSupportedType() {
    return NodeTypeEnum.START_NODE;
  }

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    try {
      // 模拟处理时间
      Thread.sleep(100);
//      return new ExecutionResult(
//          node.getId(),
//          NodeStatusEnum.SUCCESS,
//          "开始节点执行成功",
//          LocalDateTime.now()
//      );
    } catch (Exception e) {
//      throw new RuntimeException("开始节点执行失败", e);
    }
    return null;

  }
}
