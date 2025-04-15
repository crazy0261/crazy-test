package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:27
 * @DESRIPTION
 */

@Service
public class TestCaseNodeServiceImp implements NodeService {

  @Autowired
  ProcessCaseNodeRepositoryService processCaseNodeRepositoryService;

  @Override
  public NodeTypeEnum getSupportedType() {
    return null;
  }

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ProcessCaseNode processCaseNode =processCaseNodeRepositoryService.detail(context.getProjectId(),Long.valueOf(node.getId()));
    return null;
  }
}
