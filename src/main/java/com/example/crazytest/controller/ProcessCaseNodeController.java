package com.example.crazytest.controller;


import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.ProcessCaseNodeReq;
import com.example.crazytest.services.ProcessCaseNodeService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ProcessCaseNodeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 场景测试用例节点详情 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Tag(name = "场景测试用例节点详情管理")
@RestController
@RequestMapping("/process/case/node")
public class ProcessCaseNodeController {

  @Autowired
  ProcessCaseNodeService processCaseNodeService;

  @Operation(summary = "查询详情")
  @GetMapping("/detail")
  public Result<ProcessCaseNodeVO> detail(@RequestParam(value = "id") Long id) {
    return Result.success(processCaseNodeService.detail(id));
  }

  @Operation(summary = "节点保存")
  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody ProcessCaseNodeReq processCaseNodeReq) {
    return Result.success(processCaseNodeService.save(processCaseNodeReq));
  }

}
