package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.ProcessCaseNodeReq;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.ProcessCaseNodeService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ProcessCaseNodeVO;
import com.example.crazytest.vo.ProcessCaseVO;
import java.util.List;
import java.util.Objects;
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
  public ProcessCaseNodeVO detail(Long id) {
    ProcessCaseNodeVO processCaseNodeVO = new ProcessCaseNodeVO();
    ProcessCaseNode processCaseNode = processor.detail(BaseContext.getSelectProjectId(), id);

    if (Objects.isNull(processCaseNode)){
      return processCaseNodeVO;
    }

    BeanUtils.copyProperties(processCaseNode, processCaseNodeVO);

    ProcessCaseVO processCaseVO = processCaseService.detail(processCaseNode.getCaseId());
    processCaseNodeVO.setName(getNodeName(processCaseVO.getNodeArray(), id));

    return processCaseNodeVO;
  }

  @Override
  public Boolean save(ProcessCaseNodeReq processCaseNodeReq) {

    ProcessCaseNode processCaseNode = new ProcessCaseNode();
    BeanUtils.copyProperties(processCaseNodeReq, processCaseNode);
    processCaseNode.setProjectId(BaseContext.getSelectProjectId());
    processCaseNode.setAssertList(Optional.ofNullable(processCaseNodeReq.getAssertsArray()).map(
        JSONArray::toJSONString).orElse("[]"));

    processCaseService
        .updateNodeAOrEdge(processCaseNodeReq.getCaseId(), processCaseNodeReq.getNodes(),
            processCaseNodeReq.getEdges());
    return processor.saveOrUpdate(processCaseNode);
  }

  @Override
  public String getNodeName(List<JSONObject> nodes, Long nodeId) {
    return nodes.stream().filter(node -> node.getString("id").equals(String.valueOf(nodeId)))
        .map(node -> node.getString("data"))
        .map(JSON::parseObject)
        .map(data -> data.getString("label"))
        .findFirst()
        .orElse("");
  }
}
