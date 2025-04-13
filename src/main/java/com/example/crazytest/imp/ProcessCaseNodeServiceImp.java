package com.example.crazytest.imp;

import com.alibaba.fastjson.JSONArray;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.ProcessCaseNodeReq;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.ProcessCaseNodeService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.utils.BaseContext;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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

  @Autowired
  ProcessCaseService processCaseService;

  @Override
  public ProcessCaseNode detail(Long id) {
    return processor.detail(BaseContext.getSelectProjectId(), id);
  }

  @Override
  public Boolean save(ProcessCaseNodeReq processCaseNodeReq) {

    ProcessCaseNode processCaseNode = new ProcessCaseNode();
    BeanUtils.copyProperties(processCaseNodeReq, processCaseNode);
    processCaseNode.setProjectId(BaseContext.getSelectProjectId());
    processCaseNode.setAssertList(Optional.ofNullable(processCaseNodeReq.getAssertArray()).map(
        JSONArray::toJSONString).orElse("[]"));

    processCaseService.updateNodeAOrEdge(processCaseNodeReq.getCaseId(), processCaseNodeReq.getNodes(), processCaseNodeReq.getEdges());
    return processor.saveOrUpdate(processCaseNode);
  }
}
