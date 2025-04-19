package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.FlowEngineService;
import com.example.crazytest.services.FlowExecutorService;
import com.example.crazytest.utils.JsonParserUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:21
 * @DESRIPTION
 */

@Service
public class FlowEngineServiceImp implements FlowEngineService {

  @Autowired
  JsonParserUtil jsonParser;
  @Autowired
  FlowExecutorService flowExecutor;
  @Autowired
  FlowValidatorImp flowValidator;

  @Autowired
  ApiCaseService apiCaseService;

  @Override
  @Async("taskThreadPool")
  public void executeFlow(String nodesJson, String edgesJson,
      ExecutionProcessContext context) {

    List<Node> nodes = jsonParser.parseNodes(nodesJson);
    List<Edge> edges = jsonParser.parseEdges(edgesJson);
    flowExecutor.execute(nodes, edges, context);
  }

}
