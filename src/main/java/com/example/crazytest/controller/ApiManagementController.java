package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.services.ImportApiService;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ApiTypeVO;
import com.example.crazytest.vo.ImportApiVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
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
 * 接口管理表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-13
 */
@RestController
@Tag(name = "接口管理")
@RequestMapping("/api/management")
public class ApiManagementController {

  @Autowired
  ApiManagementService apiManagementService;

  @Autowired
  ImportApiService importerService;

  @PostMapping("/list")
  @Operation(summary = "查询所有接口")
  public Result<List<ApiManagementVO>> listAll(@RequestBody ApiManagementReq apiManagementReq) {
    IPage<ApiManagementVO> apiManagementVoPage = apiManagementService.listAll(apiManagementReq);
    return Result.success(apiManagementVoPage.getRecords(), apiManagementVoPage.getTotal());
  }

  @GetMapping("/get/id")
  @Operation(summary = "根据id查询接口")
  public Result<ApiManagement> getById(@RequestParam(required = true) Long id) {
    return Result.success(apiManagementService.getById(id));
  }

  @PostMapping("/save")
  @Operation(summary = "保存接口")
  public Result<Boolean> save(@RequestBody ApiManagement apiManagement) {
    return Result.success(apiManagementService.save(apiManagement));
  }

  @PostMapping("/batch/update/type")
  @Operation(summary = "批量修改类型")
  public Result<Boolean> batchUpdateType(@RequestBody ApiTypeVO apiTypeVO) {
    return Result.success(apiManagementService.batchUpdateType(apiTypeVO));
  }

  @PostMapping("/batch/update/delete")
  @Operation(summary = "批量删除")
  public Result<Boolean> batchDelete(@RequestBody ApiTypeVO apiTypeVO) {
    return Result.success(apiManagementService.batchDelete(apiTypeVO));
  }

  @PostMapping("/batch/update/move")
  @Operation(summary = "批量移动")
  public Result<Boolean> batchMove(@RequestBody ApiTypeVO apiTypeVO) {
    return Result.success(apiManagementService.batchMove(apiTypeVO));
  }

  @PostMapping("/batch/update/setPriority")
  @Operation(summary = "批量设置优先级")
  public Result<Boolean> batchSetPriority(@RequestBody ApiTypeVO apiTypeVO) {
    return Result.success(apiManagementService.batchSetPriority(apiTypeVO));
  }

  @PostMapping("/curl/import")
  @Operation(summary = "curl导入接口")
  public Result<Boolean> importCUrlImport(@RequestBody ImportApiVO imp) {
    return Result.success(importerService.importCurlApi(imp));
  }

  @PostMapping("/swagger/import")
  @Operation(summary = "swagger导入接口")
  public Result<Void> importSwaggerImport(@RequestBody ImportApiVO imp) throws IOException {
    importerService.importSwaggerApi(imp);
    return Result.success();
  }

}
