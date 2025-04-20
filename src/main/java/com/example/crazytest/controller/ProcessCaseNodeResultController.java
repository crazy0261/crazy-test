package com.example.crazytest.controller;


import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.services.ProcessCaseNodeResultService;
import com.example.crazytest.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 场景用例节点执行结果 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Tag(name = "场景用例节点执行结果")
@RestController
@RequestMapping("/process/case/node/result")
public class ProcessCaseNodeResultController {

  @Autowired
  ProcessCaseNodeResultService processCaseNodeResultService;

  @GetMapping("/detail")
  @Operation(summary = "查询场景用例节点执行结果详情")
  public Result<ProcessCaseNodeResult> detail(@RequestParam(value = "resultId") Long resultId,
      @RequestParam(value = "nodeId") Long nodeId) {
    return Result.success(processCaseNodeResultService.detailNode(resultId, nodeId));
  }

}
