package com.example.crazytest.entity.req;

import lombok.Builder;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/23 14:31
 * @DESRIPTION
 */

@Data
public class ApiDebugReq {

  private Long id;
  private Long envId;
  private String inputParams;
  private String mode;
}
