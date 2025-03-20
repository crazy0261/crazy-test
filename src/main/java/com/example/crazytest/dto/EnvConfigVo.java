package com.example.crazytest.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 20:50
 * @DESRIPTION
 */

@Data
public class EnvConfigVo {

  @ApiModelProperty(value = "应用id")
  private String appId;

  @ApiModelProperty(value = "应用名")
  private String appName;

  @ApiModelProperty(value = "env_name.id")
  private Long envNameId;

  @ApiModelProperty(value = "环境名")
  private String name;

  @ApiModelProperty(value = "域名id")
  private Long domainId;

  @ApiModelProperty(value = "域名name")
  private String domainName;


  @ApiModelProperty(value = "环境变量")
  private String envVariables;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "创建者id")
  private Long createById;

  @ApiModelProperty(value = "创建者")
  private String createByName;

  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "修改者id")
  private Long updateById;

  @ApiModelProperty(value = "更新者")
  private String updateByName;

}
