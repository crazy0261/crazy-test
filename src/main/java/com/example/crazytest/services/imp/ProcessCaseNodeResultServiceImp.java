package com.example.crazytest.services.imp;

import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.repository.ProcessCaseNodeResultRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.services.ProcessCaseNodeResultService;
import com.example.crazytest.vo.ProcessCaseNodeResultVO;
import org.springframework.beans.BeanUtils;
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

  @Autowired
  ProcessCaseResultRepositoryService caseResultRepositoryService;

  @Override
  public ProcessCaseNodeResultVO detailNode(Long resultId, Long nodeId) {
    ProcessCaseNodeResultVO processCaseNodeResultVO = new ProcessCaseNodeResultVO();
    ProcessCaseNodeResult processCaseNodeResult = processor.getDetail(resultId, nodeId);
    ProcessCaseRecord processCaseRecord = caseResultRepositoryService.getById(resultId);

    BeanUtils.copyProperties(processCaseNodeResult, processCaseNodeResultVO);
    processCaseNodeResultVO.setEnvSortId(processCaseRecord.getEnvSortId());

    return processCaseNodeResultVO;
  }
}
