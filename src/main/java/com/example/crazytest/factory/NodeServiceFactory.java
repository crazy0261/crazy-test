package com.example.crazytest.factory;

import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.services.imp.ConditionNodeServiceImp;
import com.example.crazytest.services.imp.EndNodeServiceImp;
import com.example.crazytest.services.imp.PreStepNodeServiceImp;
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

  public NodeService getService(NodeTypeEnum nodeType) {
    switch (nodeType) {
      case START_NODE:
        return startNodeService;
      case TEST_CASE_NODE:
        return testCaseNodeService;
      case PRE_STEP_NODE:
        return preStepNodeService;
      case SUB_PROCESS_NODE:
        return subProcessNodeService;
      case CONDITION_NODE:
        return conditionNodeService;
      case END_NODE:
        return endNodeService;
      default:
        throw new IllegalArgumentException("不存在类型: " + nodeType);
    }
  }
}
