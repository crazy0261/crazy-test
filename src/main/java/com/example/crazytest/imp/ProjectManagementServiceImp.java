package com.example.crazytest.imp;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.example.crazytest.services.ProjectManagementService;
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
public class ProjectManagementServiceImp implements ProjectManagementService {

  @Autowired
  ProjectManagementRepositoryService projectManagementService;

  @Override
  public IPage<ProjectManagement> list(String name, Integer current, Integer pageSize) {
    return projectManagementService.list(name, current, pageSize);
  }

  @Override
  public boolean save(ProjectManagement projectManagement) {
    projectManagement.setTenantId(Optional.ofNullable(projectManagement.getTenantId())
        .orElse(UUID.randomUUID().toString().replace("-", "").substring(0, 8)));
    return projectManagementService.saveOrUpdate(projectManagement);
  }

  @Override
  public Boolean delete(Long id) {
    return projectManagementService.removeById(id) ;
  }
}
