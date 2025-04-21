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
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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

  /**
   * 获取节点详情
   * @param id
   * @return
   */
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

  /**
   * 保存节点
   * @param processCaseNodeReq
   * @return
   */
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

  /**
   * 获取节点名称
   * @param nodes
   * @param nodeId
   * @return
   */
  @Override
  public String getNodeName(List<JSONObject> nodes, Long nodeId) {
    return nodes.stream().filter(node -> node.getString("id").equals(String.valueOf(nodeId)))
        .map(node -> node.getString("data"))
        .map(JSON::parseObject)
        .map(data -> data.getString("label"))
        .findFirst()
        .orElse("");
  }

  /**
   * 获取节点 接口用例没断言
   * @return
   */
  @Override
  public Map<Long, Integer> getAssetsNotCount() {
    List<ProcessCaseNode> processCaseNodeList =processor.getAssetsNotList(BaseContext.getSelectProjectId());
    return processCaseNodeList.stream().collect(
        Collectors.toMap(ProcessCaseNode::getUpdateById, v -> 1, Integer::sum));
  }
}
