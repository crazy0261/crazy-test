package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.entity.req.ProjectManagementReq;
import com.example.crazytest.services.ProjectManagementService;
import com.example.crazytest.utils.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 项目管理 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@RestController
@RequestMapping("/project/management")
public class ProjectManagementController {

  @Autowired
  ProjectManagementService projectManagementService;

  @PostMapping("/list")
  public Result<List<ProjectManagement>> list(ProjectManagementReq projectManagementReq) {
    IPage<ProjectManagement> projectManagementResult = projectManagementService
        .list(projectManagementReq);
    return Result.success(projectManagementResult.getRecords(), projectManagementResult.getTotal());
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody ProjectManagement projectManagement) {
    return Result.success(projectManagementService.save(projectManagement));
  }

}
