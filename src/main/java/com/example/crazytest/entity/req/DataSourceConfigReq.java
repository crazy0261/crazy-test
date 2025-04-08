package com.example.crazytest.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/8 22:08
 * @DESRIPTION
 */

@Data
public class DataSourceConfigReq {

  private Long id;

  @ApiModelProperty(value = "环境id")
  private Long envId;

  @ApiModelProperty(value = "数据库名称")
  private String name;

  @ApiModelProperty(value = "数据库名称")
  private String dbName;

  @ApiModelProperty(value = "数据库连接URL")
  private String url;

  @ApiModelProperty(value = "数据库账号")
  private String username;

  @ApiModelProperty(value = "数据库密码")
  private String password;

  @ApiModelProperty(value = "数据库驱动类")
  private String driverClassName;

  @ApiModelProperty(value = "最小连接池大小")
  private int minPoolSize;

  @ApiModelProperty(value = "最大连接池大小")
  private int maxPoolSize;

  @ApiModelProperty(value = "最大空闲时间")
  private Long maxIdleTime;

  @ApiModelProperty(value = "空闲连接测试周期")
  private Long idleConnectionTestPeriod;

  @ApiModelProperty(value = "备注")
  private String remark;

}
