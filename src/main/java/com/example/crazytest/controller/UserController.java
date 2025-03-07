package com.example.crazytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


  @GetMapping(value = "/hello")
  public String hello() {
    return "hello";
  }


}
