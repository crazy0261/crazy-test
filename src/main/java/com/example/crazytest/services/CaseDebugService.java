package com.example.crazytest.services;


import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;


public interface CaseDebugService {

  ResultApiVO processApiCaseDebug(ProcessCaseNode processCaseNode, ExecutionProcessContext context)
      throws IOException;


}
