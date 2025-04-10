package com.example.crazytest.imp;

import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.ProcessCaseNodeService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/10 22:35
 * @DESRIPTION
 */

@Service
public class ProcessCaseNodeServiceImp implements ProcessCaseNodeService {

  @Autowired
  ProcessCaseNodeRepositoryService processor;

  @Override
  public ProcessCaseNode detail(Long id) {
    return processor.detail(BaseContext.getSelectProjectId(), id);
  }

  @Override
  public Boolean save(ProcessCaseNode processCaseNode) {
    return processor.saveOrUpdate(processCaseNode);
  }
}
