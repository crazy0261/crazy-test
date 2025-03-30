package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.services.ProjectManagementService;
import com.example.crazytest.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@Tag(name = "项目管理")
@RequestMapping("/project/management")
public class ProjectManagementController {

  @Autowired
  ProjectManagementService projectManagementService;

  @GetMapping("/list")
  @Operation(summary = "查询所有项目")
  public Result<List<ProjectManagement>> list(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    IPage<ProjectManagement> projectManagementResult = projectManagementService
        .list(name, current, pageSize);
    return Result.success(projectManagementResult.getRecords(), projectManagementResult.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存项目")
  public Result<Boolean> save(@RequestBody ProjectManagement projectManagement) {
    return Result.success(projectManagementService.save(projectManagement));
  }

  @PostMapping("/delete")
  @Operation(summary = "删除项目")
  public Result<Boolean> del(@RequestBody ProjectManagement projectManagement) {
    return Result.success(projectManagementService.delete(projectManagement.getId()));
  }

}
