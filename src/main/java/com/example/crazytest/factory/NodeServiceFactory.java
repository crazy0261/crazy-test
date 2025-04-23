package com.example.crazytest.factory;

import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.services.imp.ConditionNodeServiceImp;
import com.example.crazytest.services.imp.EndNodeServiceImp;
import com.example.crazytest.services.imp.PreStepNodeServiceImp;
import com.example.crazytest.services.imp.SqlNodeServiceImp;
import com.example.crazytest.services.imp.StartNodeServiceImp;
import com.example.crazytest.services.imp.SubProcessNodeServiceImp;
import com.example.crazytest.services.imp.TestCaseNodeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:33
 * @DESRIPTION 节点工厂
 */

@Component
@RequiredArgsConstructor
public class NodeServiceFactory {

  private final StartNodeServiceImp startNodeService;
  private final TestCaseNodeServiceImp testCaseNodeService;
  private final PreStepNodeServiceImp preStepNodeService;
  private final SubProcessNodeServiceImp subProcessNodeService;
  private final ConditionNodeServiceImp conditionNodeService;
  private final EndNodeServiceImp endNodeService;
  private final SqlNodeServiceImp sqlNodeService;

  public NodeService getService(String nodeType) {
    switch (nodeType) {
      case "StartNode":
        return startNodeService;
      case "TestCaseNode":
        return testCaseNodeService;
      case "PreStepNode":
        return preStepNodeService;
      case "SubProcessNode":
        return subProcessNodeService;
      case "ConditionNode":
        return conditionNodeService;
      case "SqlNode":
        return sqlNodeService;
      case "EndNode":
        return endNodeService;
      default:
        throw new IllegalArgumentException("不存在类型: " + nodeType);
    }
  }
}
