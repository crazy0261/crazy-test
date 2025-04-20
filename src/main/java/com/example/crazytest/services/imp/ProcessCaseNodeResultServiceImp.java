package com.example.crazytest.services.imp;

import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.repository.ProcessCaseNodeResultRepositoryService;
import com.example.crazytest.services.ProcessCaseNodeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/20 18:53
 * @DESRIPTION
 */

@Service
public class ProcessCaseNodeResultServiceImp implements ProcessCaseNodeResultService {

  @Autowired
  ProcessCaseNodeResultRepositoryService processor;

  @Override
  public ProcessCaseNodeResult detailNode(Long resultId, Long nodeId) {
    return processor.getDetail(resultId, nodeId);
  }
}
