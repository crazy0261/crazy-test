package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.services.TestAccountService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.TestAccountVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/test/account")
public class TestAccountController {

  @Autowired
  TestAccountService testAccountService;

  @GetMapping("/list")
  public Result<List<TestAccountVO>> list(String name, String account, String envName, int current,
      int pageSize) {
    IPage<TestAccountVO> resultPage = testAccountService
        .list(name, account, envName, current, pageSize);
    return Result.success(resultPage.getRecords(), resultPage.getTotal());
  }

}
