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
 * @date 2025/4/14 21:28
 * @DESRIPTION
 */

@Service
public class ConditionNodeServiceImp implements NodeService {

  @Override
  public NodeTypeEnum getSupportedType() {
    return null;
  }

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    return null;
  }
}
