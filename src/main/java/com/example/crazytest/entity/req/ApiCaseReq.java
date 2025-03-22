package com.example.crazytest.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 14:49
 * @DESRIPTION
 */

@Data
public class ApiCaseReq  {

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "用例名")
  private String name;

  @ApiModelProperty(value = "负责人id")
  private Long ownerId;

  @ApiModelProperty(value = "接口路径")
  private String urlPath;

  @ApiModelProperty(value = "状态")
  boolean status;

  @ApiModelProperty(value = "优先级")
  private Integer priority;

  @ApiModelProperty(value = "执行结果")
  private String recentExecResult;

}
