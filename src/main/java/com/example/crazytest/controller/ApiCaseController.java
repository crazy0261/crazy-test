package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ApiCaseVO;
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

  @GetMapping("/list")
  public Result<List<ApiCaseVO>> list(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "appId", required = false) Long appId,
      @RequestParam(value = "path", required = false) String path,
      @RequestParam(value = "status", required = false) Boolean status,
      @RequestParam(value = "recentExecResult", required = false) String recentExecResult,
      @RequestParam(value = "ownerId", required = false) Long ownerId,
      @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    IPage<ApiCaseVO> result = apiCaseService
        .list(name, appId, path, status, recentExecResult, ownerId, current, pageSize);
    return Result.success(result.getRecords(), result.getTotal());
  }

  @GetMapping("/getById")
  public Result<ApiCaseVO> getById(Long id) {
    return Result.success(apiCaseService.getById(id));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody ApiCase apiCase) {
    return Result.success(apiCaseService.save(apiCase));
  }

  @PostMapping("/debug")
  public Result<Boolean> debug(@RequestBody ApiDebugReq apiCaseReq) {

    return Result.success();
  }
}
