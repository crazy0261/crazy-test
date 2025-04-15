package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import java.util.Map;

public interface ProcessCaseResultService {

  Long insert(ProcessCase processCase, ExecutionProcessContext context);

  void updateNodes(Long id, Map<String, Node> nodes, String status);


}
