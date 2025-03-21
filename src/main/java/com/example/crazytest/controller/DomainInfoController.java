package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.services.DomainInfoService;
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
 * 域名信息表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-21
 */
@RestController
@RequestMapping("/domain")
public class DomainInfoController {

  @Autowired
  DomainInfoService domainInfoService;

  @GetMapping("/list")
  public Result<List<DomainInfo>> list(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "urlPath", required = false) String urlPath,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

    IPage<DomainInfo> resultPage = domainInfoService.list(name, urlPath, current, pageSize);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody DomainInfo domainInfo) {
    return Result.success(domainInfoService.save(domainInfo));
  }

}
