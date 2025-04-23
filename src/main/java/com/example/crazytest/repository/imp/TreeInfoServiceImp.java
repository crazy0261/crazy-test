package com.example.crazytest.repository.imp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.entity.TreeNode;
import com.example.crazytest.entity.req.TreeReq;
import com.example.crazytest.repository.TreeInfoRepositoryService;
import com.example.crazytest.services.TreeInfoService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.TreeInfoVo;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/5 15:31
 * @DESRIPTION
 */

@Service
public class TreeInfoServiceImp implements TreeInfoService {

  @Autowired
  TreeInfoRepositoryService treeInfoRepositoryService;

  @Override
  public TreeInfoVo treeInfoByProjectId() {
    TreeInfoVo treeInfoVo = new TreeInfoVo();
    TreeInfo treeInfo = treeInfoRepositoryService
        .treeInfoByProjectId(BaseContext.getSelectProjectId());
    BeanUtils.copyProperties(treeInfo, treeInfoVo);
    treeInfoVo.setTreeList(JSONUtil.parseArray(treeInfo.getTreeData()).toList(TreeNode.class));
    return treeInfoVo;
  }

  @Override
  public Boolean treeSave(TreeInfo treeInfo) {
    TreeInfo treeInfoData = treeInfoRepositoryService
        .treeInfoByProjectId(BaseContext.getSelectProjectId());
    treeInfoData.setTreeData(treeInfo.getTreeData());
    return treeInfoRepositoryService.saveOrUpdate(treeInfoData);
  }

  @Override
  public boolean save(TreeInfo treeInfo) {
    return treeInfoRepositoryService.saveOrUpdate(treeInfo);
  }

  @Override
  public void saveNode(TreeReq treeReq) {
    addNode(treeReq.getCurrentKey(), treeReq.getNewNodeTitle());
  }

  @Override
  public void addNode(String currentKey, String newNodeTitle) {
    TreeInfoVo treeInfoVo = treeInfoByProjectId();
    findNodeAndAddChild(treeInfoVo.getProjectId(), treeInfoVo.getTreeList(), currentKey,
        newNodeTitle);
  }

  @Override
  public void findNodeAndAddChild(Long projectId, List<TreeNode> treeData, String currentKey,
      String newNodeTitle) {
    if (StringUtils.isEmpty(currentKey)) {
      treeData.add(new TreeNode(UUID.randomUUID().toString(), newNodeTitle));
      treeInfoRepositoryService.treeInfoProjectIdSave(projectId, JSON.toJSONString(treeData));
      return;
    }

    boolean isCheckNode = traverseAndAddChild(treeData, currentKey, newNodeTitle);

    if (isCheckNode) {
      treeInfoRepositoryService.treeInfoProjectIdSave(projectId, JSON.toJSONString(treeData));
    }
  }

  @Override
  public boolean traverseAndAddChild(List<TreeNode> treeData, String currentKey,
      String newNodeTitle) {
    for (TreeNode node : treeData) {
      if (Objects.equals(node.getKey(), currentKey)) {
        node.getChildren().add(new TreeNode(UUID.randomUUID().toString(), newNodeTitle));
        return Boolean.TRUE;
      }
      if (CollUtil.isNotEmpty(node.getChildren()) && traverseAndAddChild(node.getChildren(),
          currentKey, newNodeTitle)) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  @Override
  public void updateNode(TreeReq treeReq) {
    TreeInfoVo treeInfoVo = treeInfoByProjectId();
    findNodeAndUpdate(treeInfoVo.getProjectId(), treeInfoVo.getTreeList(), treeReq.getCurrentKey(),
        treeReq.getNewNodeTitle());
  }

  @Override
  public void findNodeAndUpdate(Long projectId, List<TreeNode> treeData, String key,
      String newTitle) {
    for (TreeNode node : treeData) {
      if (Objects.equals(node.getKey(), key)) {
        node.setTitle(newTitle);
        return;
      }
      findNodeAndUpdate(projectId, node.getChildren(), key, newTitle);
    }
    treeInfoRepositoryService.treeInfoProjectIdSave(projectId, JSON.toJSONString(treeData));
  }

  @Override
  public void deleteNode(TreeReq treeReq) {
    TreeInfoVo treeInfoVo = treeInfoByProjectId();
    treeInfoVo.getTreeList().removeIf(node -> deleteNodeRecursive(node, treeReq.getCurrentKey()));
    treeInfoRepositoryService.treeInfoProjectIdSave(BaseContext.getSelectProjectId(),
        JSON.toJSONString(treeInfoVo.getTreeList()));
  }

  @Override
  public boolean deleteNodeRecursive(TreeNode node, String key) {
    if (Objects.equals(node.getKey(), key)) {
      return Boolean.TRUE;
    }

    if (CollUtil.isNotEmpty(node.getChildren())) {
      node.getChildren().removeIf(child -> deleteNodeRecursive(child, key));
    }

    return Boolean.FALSE;
  }
}
