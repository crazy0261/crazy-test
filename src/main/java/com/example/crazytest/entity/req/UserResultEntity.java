package com.example.crazytest.entity.req;

import java.time.LocalDateTime;
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

  private String createByName;

  private LocalDateTime createTime;


  private String updateByName;

  private LocalDateTime updateTime;


}
