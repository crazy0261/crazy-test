package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.services.TestAccountService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.TestAccountVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试账号表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-20
 */
@RestController
@Tag(name = "测试账号管理")
@RequestMapping("/test/account")
public class TestAccountController {

  @Autowired
  TestAccountService testAccountService;

  @GetMapping("/list")
  @Operation(summary = "分页查询账号")
  public Result<List<TestAccountVO>> list(String name, String genTokenStatus,
      int current, int pageSize) {
    IPage<TestAccountVO> resultPage = testAccountService
        .list(name, genTokenStatus, current, pageSize);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存账号")
  public Result<Boolean> save(@RequestBody TestAccount testAccount) {
    return Result.success(testAccountService.save(testAccount));
  }

  @PostMapping("/del")
  @Operation(summary = "删除账号")
  public Result<Boolean> del(@RequestBody TestAccount testAccount) {
    return Result.success(testAccountService.delete(testAccount.getId()));
  }

  @PostMapping("/token")
  @Operation(summary = "更新token")
  public Result<Void> updateToken(@RequestBody TestAccount testAccount) throws IOException {
    testAccountService.crateManualToken(testAccount.getId());
    return Result.success();
  }

}
