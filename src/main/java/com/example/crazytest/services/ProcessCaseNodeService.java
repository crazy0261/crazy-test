package com.example.crazytest.services;

import com.example.crazytest.entity.ProcessCaseNode;

public interface ProcessCaseNodeService {

  ProcessCaseNode detail(Long id);

  Boolean save(ProcessCaseNode processCaseNode);

}
