package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DataSourceConfig;
import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.example.crazytest.services.DataSourceConfigService;
import com.example.crazytest.utils.Result;
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
 * 数据库配置 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-08
 */
@Tag(name = "数据库配置")
@RestController
@RequestMapping("/data/source/config")
public class DataSourceConfigController {

  @Autowired
  private DataSourceConfigService dataSourceService;

  @GetMapping("/list")
  @Operation(summary = "查询数据库配置列表")
  public Result<List<DataSourceConfig>> list(@RequestParam(required = false) String name,
      @RequestParam(required = false, defaultValue = "1") Integer current,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
    IPage<DataSourceConfig> page = dataSourceService.list(name, current, pageSize);
    return Result.success(page.getRecords(), page.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存数据库配置列表")
  public Result<Boolean> list(@RequestBody DataSourceConfigReq dataSourceConfigReq) {
    return Result.success(dataSourceService.save(dataSourceConfigReq));
  }

  @PostMapping("/connection")
  @Operation(summary = "测试数据库连接")
  public Result<Boolean> testConnection(@RequestBody DataSourceConfigReq dataSourceConfigReq) {
    return Result.success(dataSourceService.testConnection(dataSourceConfigReq));
  }

  @PostMapping("/del")
  @Operation(summary = "删除数据库配置")
  public Result<Boolean> del(@RequestBody DataSourceConfigReq dataSourceConfigReq) {
    return Result.success(dataSourceService.del(dataSourceConfigReq.getId()));
  }

}
