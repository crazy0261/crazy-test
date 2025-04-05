package com.example.crazytest.repository;

import com.example.crazytest.entity.TreeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 树结构管理 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
public interface TreeInfoRepositoryService extends IService<TreeInfo> {

  TreeInfo treeInfoByProjectId(Long projectId);

  void treeInfoProjectIdSave(Long projectId,String treeList);



}
