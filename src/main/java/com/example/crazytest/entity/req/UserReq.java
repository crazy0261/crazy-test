package com.example.crazytest.entity.req;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/11 22:58
 * @DESRIPTION
 */

@Data
public class UserReq {

  String account;
  String password;
  Long projectId;

}
