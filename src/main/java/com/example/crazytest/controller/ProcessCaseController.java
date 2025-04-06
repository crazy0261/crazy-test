package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ProcessCaseDTO;
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
    IPage<ProcessCaseVO> listPage = processCaseService.listPage(processCaseDTO);
    return Result.success(listPage.getRecords(), listPage.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存场景用例")
  public Result<Boolean> save(@RequestBody ProcessCaseReq processCaseReq) {
    return Result.success(processCaseService.save(processCaseReq));
  }


}
