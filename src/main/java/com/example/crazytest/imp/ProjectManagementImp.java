package com.example.crazytest.imp;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.entity.req.ProjectManagementReq;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.example.crazytest.services.ProjectManagementService;
import com.example.crazytest.utils.BaseContext;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 20:31
 * @DESRIPTION
 */

@Service
public class ProjectManagementImp implements ProjectManagementService {

  @Autowired
  ProjectManagementRepositoryService projectManagementService;

  @Override
  public IPage<ProjectManagement> list(ProjectManagementReq projectManagementReq) {
    return projectManagementService.list(projectManagementReq);
  }

  @Override
  public boolean save(ProjectManagement projectManagement) {
    projectManagement.setTenantId(Optional.ofNullable(projectManagement.getTenantId())
        .orElse(UUID.randomUUID().toString().replace("-", "").substring(0, 8)));
    return projectManagementService.saveOrUpdate(projectManagement);
  }

  @Override
  public boolean delete(ProjectManagement projectManagement) {
    projectManagement.setIsDelete(1);
    return projectManagementService.saveOrUpdate(projectManagement);
  }
}
