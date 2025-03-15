package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.entity.req.ProjectManagementReq;

public interface ProjectManagementService {

  IPage<ProjectManagement> list(ProjectManagementReq projectManagementReq);

  boolean save(ProjectManagement projectManagement);

  boolean delete(ProjectManagement projectManagement);

}
