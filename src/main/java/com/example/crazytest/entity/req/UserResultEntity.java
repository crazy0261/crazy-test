package com.example.crazytest.entity.req;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/12 23:25
 * @DESRIPTION
 */

@Data
public class UserResultEntity {

  private String tenantId;

  private String account;

  private String name;


  private String email;

  private String phone;


  private Boolean status;

}
