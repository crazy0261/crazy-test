package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.vo.ApplicationManagementVO;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.utils.Result;
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
@RequestMapping("/application/management")
public class ApplicationManagementController {

  @Autowired
  ApplicationManagementService appManagementService;

  @GetMapping("/list")
  public Result<List<ApplicationManagementVO>> list(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "负责人", required = false) Long ownerId,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

    IPage<ApplicationManagementVO> resultPage = appManagementService.list(name, ownerId, current, pageSize);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody ApplicationManagementReq applicationManagementReq) {
    return Result.success(appManagementService.save(applicationManagementReq));
  }

}
