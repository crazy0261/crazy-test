package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ApiCaseResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 接口执行结果 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
@RestController
@Tag(name = "接口执行结果管理")
@RequestMapping("/api/case/result")
public class ApiCaseResultController {

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @GetMapping("/list")
  public Result<List<ApiCaseResultVo>> list(
      @RequestParam(value = "apiTestcaseId", required = true) String apiTestcaseId,
      @RequestParam(value = "current", required = true) Integer current,
      @RequestParam(value = "pageSize", required = true) Integer pageSize) {
    IPage<ApiCaseResultVo> resultPage = apiCaseResultService.list(apiTestcaseId, current, pageSize);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

  @GetMapping("/query")
  public Result<ApiCaseResult> query(@RequestParam(value = "id", required = true) Long id) {
    return Result.success(apiCaseResultService.queryById(id));
  }

}
