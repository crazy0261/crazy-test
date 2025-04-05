package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.mapper.TreeInfoMapper;
import com.example.crazytest.repository.TreeInfoRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 树结构管理 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
@Service
public class TreeInfoRepositoryServiceImpl extends ServiceImpl<TreeInfoMapper, TreeInfo> implements
    TreeInfoRepositoryService {

  @Override
  public TreeInfo treeInfoByProjectId(Long projectId) {
    return this.lambdaQuery()
        .eq(TreeInfo::getProjectId, projectId)
        .eq(TreeInfo::getIsDelete, Boolean.FALSE)
        .one();
  }

  @Override
  public void treeInfoProjectIdSave(Long projectId, String treeList) {
    this.lambdaUpdate()
        .eq(TreeInfo::getProjectId, projectId)
        .set(TreeInfo::getTreeData, treeList)
        .update();
  }
}
