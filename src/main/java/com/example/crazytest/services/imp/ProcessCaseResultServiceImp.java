package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.convert.ProcessCaseNodeResultCovert;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseResult;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.services.ProcessCaseResultService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/15 14:42
 * @DESRIPTION
 */

@Service
public class ProcessCaseResultServiceImp implements ProcessCaseResultService {

  @Autowired
  ProcessCaseResultRepositoryService repositoryService;

  @Override
  public Long insert(ProcessCase processCase, ExecutionProcessContext context) {
    ProcessCaseResult processCaseResult = ProcessCaseNodeResultCovert
        .processCaseResultConvert(processCase, context);
    repositoryService.saveOrUpdate(processCaseResult);
    return processCaseResult.getId();
  }

  @Override
  public void updateNodes(Long id, Map<String, Node> nodes, String status) {
    repositoryService.updateNodes(id, JSON.toJSONString(nodes), status);
  }
}
