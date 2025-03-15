package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.utils.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试用例 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@RestController
@RequestMapping("/api/case")
public class ApiCaseController {

  @Autowired
  ApiCaseService apiCaseService;

  @PostMapping("/list")
  public Result<List<ApiCase>> list(@RequestBody ApiCaseReq apiCaseReq) {
    IPage<ApiCase> result = apiCaseService.list(apiCaseReq);
    return Result.success(result.getRecords(), result.getTotal());
  }

}
