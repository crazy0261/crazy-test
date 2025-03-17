package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.utils.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/application/management")
public class ApplicationManagementController {

  @Autowired
  ApplicationManagementService appManagementService;

  @GetMapping("/list")
  public Result<List<ApplicationManagement>> list(
      ApplicationManagementReq applicationManagementReq) {
    IPage<ApplicationManagement> resultPage = appManagementService.list(applicationManagementReq);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

  @PostMapping("/save")
  public Result<Boolean> save(ApplicationManagementReq applicationManagementReq) {
    return Result.success(appManagementService.save(applicationManagementReq));
  }

}
