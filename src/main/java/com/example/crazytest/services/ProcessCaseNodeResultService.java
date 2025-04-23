package com.example.crazytest.services;

import com.example.crazytest.vo.ProcessCaseNodeResultVO;

public interface ProcessCaseNodeResultService {

  ProcessCaseNodeResultVO detailNode(Long resultId,Long nodeId);

}
