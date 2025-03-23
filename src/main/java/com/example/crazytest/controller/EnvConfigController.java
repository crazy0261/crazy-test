package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.req.EnvConfigReq;
import com.example.crazytest.vo.EnvConfigVO;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.utils.Result;
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
@RequestMapping("/env/config")
public class EnvConfigController {

  @Autowired
  EnvConfigService envConfigService;

  @GetMapping("/list")
  public Result<List<EnvConfigVO>> list(
      @RequestParam(value = "appid", required = false) String appid,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "domainName", required = false) String domainName,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
    IPage<EnvConfigVO> pageEnvInfoPage = envConfigService
        .list(appid, name, domainName, current, pageSize);
    return Result.success(pageEnvInfoPage.getRecords(), pageEnvInfoPage.getTotal());
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody EnvConfigReq envConfigReq) {
    return Result.success(envConfigService.save(envConfigReq));
  }

  @GetMapping("/queryById")
  public Result<EnvConfigReq> queryById(@RequestParam(value = "id") Long id) {
    return Result.success(envConfigService.queryById(id));
  }


}
