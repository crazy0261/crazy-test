package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.req.EnvConfigReq;
import com.example.crazytest.vo.EnvConfigVO;
import com.example.crazytest.services.EnvConfigService;
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
 * 环境信息表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-19
 */
@RestController
@Tag(name = "环境信息管理")
@RequestMapping("/env/config")
public class EnvConfigController {

  @Autowired
  EnvConfigService envConfigService;

  @GetMapping("/list")
  @Operation(summary = "查询所有环境信息")
  public Result<List<EnvConfigVO>> list(
      @RequestParam(value = "appid", required = false) String appid,
      @RequestParam(value = "envName", required = false) String envName,
      @RequestParam(value = "domainName", required = false) String domainName,
      @RequestParam(value = "sort", required = false) String sort,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
    IPage<EnvConfigVO> pageEnvInfoPage = envConfigService
        .list(appid, envName, sort, domainName, current, pageSize);
    return Result.success(pageEnvInfoPage.getRecords(), pageEnvInfoPage.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存环境信息")
  public Result<Boolean> save(@RequestBody EnvConfigReq envConfigReq) {
    return Result.success(envConfigService.save(envConfigReq));
  }

  @GetMapping("/queryById")
  @Operation(summary = "根据id查询环境信息")
  public Result<EnvConfigReq> queryById(@RequestParam(value = "id") Long id) {
    return Result.success(envConfigService.queryById(id));
  }

  @GetMapping("/list/all")
  @Operation(summary = "查询所有环境信息")
  public Result<List<EnvConfig>> listAll() {
    return Result.success(envConfigService.listAll());
  }


  @PostMapping("/delete")
  @Operation(summary = "删除环境信息")
  public Result<Boolean> save(@RequestBody EnvConfig envConfig) {
    return Result.success(envConfigService.delete(envConfig.getId()));
  }


  @GetMapping("/app/list")
  @Operation(summary = "应用下所有环境")
  public Result<List<EnvConfig>> envAppList(
      @RequestParam(value = "appId", required = false) Long appId) {
    return Result.success(envConfigService.envAppList(appId));
  }

  @GetMapping("/env/sort")
  @Operation(summary = "获取环境排序")
  public Result<List<Integer>> getEnvSort() {
    return Result.success(envConfigService.getEnvSort());
  }

}
