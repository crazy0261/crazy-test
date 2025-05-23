package com.example.crazytest.controller;


import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import com.example.crazytest.entity.req.ProcessCaseReq;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ProcessCaseVO;
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
 * 场景用例 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */

@Tag(name = "场景用例")
@RestController
@RequestMapping("/process/case")
public class ProcessCaseController {

  @Autowired
  ProcessCaseService processCaseService;

  @GetMapping("/list")
  @Operation(summary = "查询场景用例列表")
  public Result<List<ProcessCaseVO>> list(
      @RequestParam(value = "treeKey", required = false) String treeKey,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "ownerId", required = false) Long ownerId,
      @RequestParam(value = "status", required = false) Integer status,
      @RequestParam(value = "recentExecResult", required = false) String recentExecResult,
      @RequestParam(value = "isSubProcess", required = false) Integer isSubProcess,
      @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

    ProcessCaseDTO processCaseDTO = ProcessCaseDTO.builder()
        .treeKey(treeKey)
        .name(name)
        .ownerId(ownerId)
        .status(status)
        .recentExecResult(recentExecResult)
        .isSubProcess(isSubProcess)
        .current(current)
        .pageSize(pageSize)
        .build();
    return Result.coverPage(processCaseService.listPage(processCaseDTO));
  }

  @PostMapping("/save")
  @Operation(summary = "保存场景用例")
  public Result<Boolean> save(@RequestBody ProcessCaseReq processCaseReq) {
    return Result.success(processCaseService.save(processCaseReq));
  }

  @PostMapping("/batch/update/owner")
  @Operation(summary = "批量修改负责人")
  public Result<Boolean> batchUpdateOwner(@RequestBody ProcessCaseBatchReq processCaseBatchReq) {
    return Result.success(processCaseService.batchUpdateOwner(processCaseBatchReq));
  }

  @PostMapping("/batch/update/move")
  @Operation(summary = "批量移动场景用例")
  public Result<Boolean> batchUpdateMove(@RequestBody ProcessCaseBatchReq processCaseBatchReq) {
    return Result.success(processCaseService.batchUpdateMove(processCaseBatchReq));
  }

  @PostMapping("/batch/update/up")
  @Operation(summary = "批量上架用例")
  public Result<Boolean> batchUpdateUpCase(@RequestBody ProcessCaseBatchReq processCaseBatchReq) {
    return Result.success(processCaseService.batchUpdateUpCase(processCaseBatchReq));
  }

  @PostMapping("/batch/update/down")
  @Operation(summary = "批量下架用例")
  public Result<Boolean> batchUpdateDownCase(@RequestBody ProcessCaseBatchReq processCaseBatchReq) {
    return Result.success(processCaseService.batchUpdateDownCase(processCaseBatchReq));
  }

  @PostMapping("/copy")
  @Operation(summary = "复制用例")
  public Result<Boolean> copy(@RequestBody ProcessCase processCase) {
    return Result.success(processCaseService.copy(processCase));
  }

  @PostMapping("/delete")
  @Operation(summary = "删除用例")
  public Result<Boolean> delete(@RequestBody ProcessCase processCase) {
    return Result.success(processCaseService.delete(processCase));
  }

  @GetMapping("/detail")
  @Operation(summary = "用例详情")
  public Result<ProcessCaseVO> detail(@RequestParam(value = "id", required = false) Long id) {
    return Result.success(processCaseService.detail(id));
  }

  @GetMapping("/sub")
  @Operation(summary = "当前项目下子流程")
  public Result<List<ProcessCase>> getIsSubProcess() {
    return Result.success(processCaseService.getIsSubProcess());
  }

  @PostMapping("/debug")
  @Operation(summary = "场景用例调试")
  public Result<String> debug(@RequestBody ApiDebugReq apiDebugReq) {
    return Result.success(processCaseService.debug(apiDebugReq));
  }

}
