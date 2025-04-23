package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.services.NodeService;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:35
 * @DESRIPTION
 */

@Service
public class EndNodeServiceImp implements NodeService {

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ExecutionResult result = new ExecutionResult();
    result.setStatus(ExecStatusEnum.SUCCESS);
    return result;
  }
}
