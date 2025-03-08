package com.example.crazytest.controller;

import com.example.crazytest.entity.UserEntity;
import com.example.crazytest.imp.UserImp;
import com.example.crazytest.utils.Result;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public Result<List<UserEntity>> listAll() {
    return Result.success(userImp.getUsers());
  }

  /**
   * 登录
   *
   * @param account
   * @param password
   * @return
   */
  @PostMapping("/login")
  public Result<String> login(@RequestParam(value = "account") String account,
      @RequestParam(value = "password") String password) {
    return Result.success(userImp.login(account, password));
  }

  @PostMapping("/save")
  public Result<Boolean> login(@RequestBody UserEntity userEntity) {
    return Result.success(userImp.save(userEntity));
  }


}
