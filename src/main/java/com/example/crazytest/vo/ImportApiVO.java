package com.example.crazytest.vo;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 23:11
 * @DESRIPTION
 */

@Data
public class ImportApiVO {

  private Long appId;
  private String apiName;
  private String curl;
  private String swaggerUrl;

}
