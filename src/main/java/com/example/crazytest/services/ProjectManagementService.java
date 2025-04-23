package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;

public interface ProjectManagementService {

  IPage<ProjectManagement> list(String name, Integer current, Integer pageSize);

  boolean save(ProjectManagement projectManagement);

  Boolean delete(Long id);

}
