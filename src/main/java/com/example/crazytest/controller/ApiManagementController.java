package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ImportApiReq;
import com.example.crazytest.services.ImportApiService;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.entity.req.ApiTypeReq;
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
  public Result<ApiManagement> getById(@RequestParam(value = "id") Long id) {
    return Result.success(apiManagementService.getById(id));
  }

  @PostMapping("/save")
  @Operation(summary = "保存接口")
  public Result<Boolean> save(@RequestBody ApiManagement apiManagement) {
    return Result.success(apiManagementService.save(apiManagement));
  }

  @PostMapping("/batch/update/type")
  @Operation(summary = "批量修改类型")
  public Result<Boolean> batchUpdateType(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchUpdateType(apiTypeReq));
  }

  @PostMapping("/batch/update/delete")
  @Operation(summary = "批量删除")
  public Result<Boolean> batchDelete(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchDelete(apiTypeReq));
  }

  @PostMapping("/batch/update/move")
  @Operation(summary = "批量移动")
  public Result<Boolean> batchMove(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchMove(apiTypeReq));
  }

  @PostMapping("/batch/update/setPriority")
  @Operation(summary = "批量设置优先级")
  public Result<Boolean> batchSetPriority(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchSetPriority(apiTypeReq));
  }

  @PostMapping("/batch/update/up")
  @Operation(summary = "批量设置上架")
  public Result<Boolean> batchUp(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchUp(apiTypeReq));
  }

  @PostMapping("/batch/update/down")
  @Operation(summary = "批量设置下架")
  public Result<Boolean> batchDown(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchDown(apiTypeReq));
  }

  @PostMapping("/batch/update/prod")
  @Operation(summary = "批量设置/取消生产")
  public Result<Boolean> batchProd(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchProd(apiTypeReq));
  }

  @PostMapping("/batch/update/owner")
  @Operation(summary = "批量设置负责人")
  public Result<Boolean> batchOUpdateOwner(@RequestBody ApiTypeReq apiTypeReq) {
    return Result.success(apiManagementService.batchOUpdateOwner(apiTypeReq));
  }

  @PostMapping("/curl/import")
  @Operation(summary = "curl导入接口")
  public Result<Boolean> importCUrlImport(@RequestBody ImportApiReq imp) {
    return Result.success(importerService.importCurlApi(imp));
  }

  @PostMapping("/swagger/import")
  @Operation(summary = "swagger导入接口")
  public Result<Void> importSwaggerImport(@RequestBody ImportApiReq imp) throws IOException {
    importerService.importSwaggerApi(imp);
    return Result.success();
  }

  @GetMapping("/app/list")
  @Operation(summary = "应用下所有用例")
  public Result<List<ApiManagement>> appApiList(@RequestParam Long appId) {
    return Result.success(apiManagementService.appApiList(appId));
  }

}
