package com.example.crazytest.services;

import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.ProcessCaseNodeReq;

public interface ProcessCaseNodeService {

  ProcessCaseNode detail(Long id);

  Boolean save(ProcessCaseNodeReq processCaseNodeReq);

}
