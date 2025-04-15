package com.example.crazytest.controller;


import com.example.crazytest.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 场景用例执行结果 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@RestController
@Tag(name = "场景用例执行结果")
@RequestMapping("/process/case/result")
public class ProcessCaseResultController {

  @PostMapping("/save")
  @Operation(summary = "保存场景用例执行结果")
  public Result<Boolean> save() {
    return Result.success(true);
  }

}
