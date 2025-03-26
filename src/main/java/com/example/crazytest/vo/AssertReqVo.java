package com.example.crazytest.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/26 22:38
 * @DESRIPTION
 */

@Data
public class AssertReqVo {

  private Long id;

  @ApiModelProperty(value = "jsonPath")
  private String jsonPath;

  @ApiModelProperty(value = "条件")
  private String condition;

  @ApiModelProperty(value = "预期值")
  private String expectValue;

}
