package com.example.crazytest.services;


import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.entity.TreeNode;
import com.example.crazytest.entity.req.TreeReq;
import com.example.crazytest.vo.TreeInfoVo;
import java.util.List;

public interface TreeInfoService {

  TreeInfoVo treeInfoByProjectId();

  Boolean treeSave(TreeInfo treeInfo);

  boolean save(TreeInfo treeInfo);

  void saveNode(TreeReq treeReq);

  void addNode(String currentKey, String newNodeTitle);

  void findNodeAndAddChild(Long projectId, List<TreeNode> treeData, String currentKey,
      String newNodeTitle);

  boolean traverseAndAddChild(List<TreeNode> treeData, String currentKey, String newNodeTitle);

  void updateNode(TreeReq treeReq);

  void findNodeAndUpdate(Long projectId, List<TreeNode> treeData, String key, String newTitle);

  void deleteNode(TreeReq treeReq);

  boolean deleteNodeRecursive(TreeNode node, String key);

}
