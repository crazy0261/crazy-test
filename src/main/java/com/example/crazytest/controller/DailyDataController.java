package com.example.crazytest.controller;

import com.example.crazytest.services.DailyDataService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.CoreIndicatorsListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 每日汇总数据 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-17
 */
@Tag(name = "每日汇总数据管理")
@RestController
@RequestMapping("/daily/data")
public class DailyDataController {

  @Autowired
  DailyDataService dataService;

  @GetMapping("/coreIndicators/detail")
  @Operation(summary = "获取核心指标列表")
  public Result<CoreIndicatorsListVO> coreIndicatorsDetail() {
    return Result.success(dataService.getCoreIndicatorsDetail());
  }
}
