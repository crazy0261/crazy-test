package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.enums.NodeTypeEnum;

public interface NodeService {

  NodeTypeEnum getSupportedType();

  ExecutionResult execute(Node node, ExecutionProcessContext context);
}
