package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.req.EncryptInfoReq;
import com.example.crazytest.services.EncryptInfoService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.EncryptInfoVO;
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
 * 加密参数表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-09
 */
@RestController
@Tag(name = "加密参数表")
@RequestMapping("/encrypt/info")
public class EncryptInfoController {

  @Autowired
  EncryptInfoService encryptInfoService;

  @GetMapping("/list")
  @Operation(summary = "查询加密参数列表")
  public Result<List<EncryptInfoVO>> list(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "current", defaultValue = "1") Integer current,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    IPage<EncryptInfoVO> encryptInfoPage = encryptInfoService.list(name, current, pageSize);
    return Result.success(encryptInfoPage.getRecords(), encryptInfoPage.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存加密参数")
  public Result<Boolean> save(@RequestBody EncryptInfoReq encryptInfo) {
    return Result.success(encryptInfoService.save(encryptInfo));
  }

  @PostMapping("/del")
  @Operation(summary = "删除加密参数")
  public Result<Boolean> del(@RequestBody EncryptInfoReq encryptInfo) {
    return Result.success(encryptInfoService.del(encryptInfo.getId()));
  }


}
