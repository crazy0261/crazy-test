package com.example.crazytest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserReq;
import com.example.crazytest.entity.req.UserResultEntity;
import com.example.crazytest.imp.UserImp;
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
 * @author
 * @name Menghui
 * @date 2025/3/7 22:57
 * @DESRIPTION
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  UserImp userImp;


  /**
   * 查询所有用户
   *
   * @return
   */
  @GetMapping("/list")
  public Result<List<UserResultEntity>> listAll(
      @RequestParam(value = "account", required = false) String account,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "phone", required = false) String phone,
      @RequestParam(value = "status", required = false) Boolean status,
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
  ) {
    IPage<UserResultEntity> users = userImp.getUsers(account, name, phone, status, page, pageSize);
    return Result.success(users.getRecords(), users.getTotal());
  }

  /**
   * 登录
   */
  @PostMapping("/login")
  public Result<String> login(@RequestBody UserReq userReq) {
    return Result.success(userImp.login(userReq.getAccount(), userReq.getPassword()));
  }

  @GetMapping("/currentUser")
  public Result<UserResultEntity> currentUser(@RequestParam String account) {
    return Result.success(userImp.currentUser(account));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody User userEntity) {
    return Result.success(userImp.save(userEntity));
  }

  @PostMapping("/resetPwd")
  public Result<Boolean> resetPwd(@RequestParam(value = "account") String account) {
    return Result.success(userImp.resetPwd(account));
  }


}
