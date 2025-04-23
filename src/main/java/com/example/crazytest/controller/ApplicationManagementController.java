package com.example.crazytest.controller;


import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.vo.ApplicationManagementVO;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.services.ApplicationManagementService;
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
 * 应用管理 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-17
 */
@RestController
@Tag(name = "应用管理")
@RequestMapping("/application/management")
public class ApplicationManagementController {

  @Autowired
  ApplicationManagementService appManagementService;

  @GetMapping("/list")
  @Operation(summary = "查询所有应用列表")
  public Result<List<ApplicationManagementVO>> list(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "负责人", required = false) Long ownerId,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

    return Result.coverPage(appManagementService.list(name, ownerId, current, pageSize));
  }

  @PostMapping("/save")
  @Operation(summary = "保存应用")
  public Result<Boolean> save(@RequestBody ApplicationManagementReq applicationManagementReq) {
    return Result.success(appManagementService.save(applicationManagementReq));
  }

  @GetMapping("/list/all")
  @Operation(summary = "查询当前租户下所有应用")
  public Result<List<ApplicationManagement>> listAll() {
    return Result.success(appManagementService.listAllApplicationManagement());
  }

  @PostMapping("/delete")
  @Operation(summary = "删除当前应用")
  public Result<Boolean> delete(@RequestBody ApplicationManagementReq applicationManagementReq) {
    return Result.success(appManagementService.delete(applicationManagementReq.getId()));
  }

}
