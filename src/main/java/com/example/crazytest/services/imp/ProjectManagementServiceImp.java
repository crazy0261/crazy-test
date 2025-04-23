package com.example.crazytest.services.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.example.crazytest.services.ProjectManagementService;
import com.example.crazytest.services.TreeInfoService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 20:31
 * @DESRIPTION
 */

@Service
public class ProjectManagementServiceImp implements ProjectManagementService {

  @Autowired
  ProjectManagementRepositoryService projectManagementService;

  @Autowired
  TreeInfoService treesInfoService;

  @Override
  public IPage<ProjectManagement> list(String name, Integer current, Integer pageSize) {
    return projectManagementService.list(name, current, pageSize);
  }

  @Override
  public boolean save(ProjectManagement projectManagement) {

    TreeInfo treeInfo = new TreeInfo();
    treeInfo.setProjectId(BaseContext.getSelectProjectId());
    treeInfo.setTreeData("[]");
    treesInfoService.save(treeInfo);
    return projectManagementService.saveOrUpdate(projectManagement);
  }

  @Override
  public Boolean delete(Long id) {
    return projectManagementService.removeById(id);
  }
}
