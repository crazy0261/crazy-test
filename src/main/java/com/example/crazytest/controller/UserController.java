package com.example.crazytest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserReq;
import com.example.crazytest.entity.req.UserResultEntity;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @author
 * @name Menghui
 * @date 2025/3/7 22:57
 * @DESRIPTION
 */

@RestController
@Tag(name = "用户管理")
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  UserService userService;


  /**
   * 查询所有用户
   *
   * @return
   */
  @GetMapping("/list")
  @Operation(summary = "查询所有用户")
  public Result<List<UserResultEntity>> listAll(
      @RequestParam(value = "account", required = false) String account,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "phone", required = false) String phone,
      @RequestParam(value = "status", required = false) Boolean status,
      @RequestParam(value = "current", required = false, defaultValue = "1") int current,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
  ) {
    IPage<UserResultEntity> users = userService
        .getUsers(account, name, phone, status, current, pageSize);
    return Result.success(users.getRecords(), users.getTotal());
  }

  /**
   * 登录
   */
  @PostMapping("/login")
  @Operation(summary = "登录")
  public Result<String> login(@RequestBody UserReq userReq) {
    return Result.success(userService.login(userReq.getAccount(), userReq.getPassword()));
  }

  @PostMapping("/update/token")
  @Operation(summary = "更新token")
  public Result<String> updateToken(@RequestBody UserReq userReq) {
    return Result.success(userService.updateToken(userReq.getProjectId()));
  }

  @GetMapping("/currentUser")
  @Operation(summary = "获取当前用户信息")
  public Result<UserResultEntity> currentUser(@RequestParam String account) {
    return Result.success(userService.currentUser(account));
  }

  @PostMapping("/save")
  @Operation(summary = "保存用户")
  public Result<Boolean> save(@RequestBody User userEntity) {
    return Result.success(userService.save(userEntity));
  }

  @PostMapping("/resetPwd")
  @Operation(summary = "重置密码")
  public Result<Boolean> resetPwd(@RequestParam(value = "account") String account) {
    return Result.success(userService.resetPwd(account));
  }

  @PostMapping("/del")
  @Operation(summary = "删除用户")
  public Result<Boolean> delete(@RequestBody User userEntity) {
    return Result.success(userService.delete(userEntity.getId()));
  }

  @GetMapping("/list/all")
  @Operation(summary = "当前企业所有用户")
  public Result<List<UserResultEntity>> listAll() {
    return Result.success(userService.listAll());
  }


}
