package com.example.crazytest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/12 09:33
 * @DESRIPTION
 */

@Data
public class DataSourceDTO {

  @ApiModelProperty(value = "数据库名")
  private String dbName;

  @ApiModelProperty(value = "数据库连接URL")
  private String jdbcUrl;

  @ApiModelProperty(value = "数据库账号")
  private String username;

  @ApiModelProperty(value = "数据库密码")
  private String password;

  @ApiModelProperty(value = "数据库驱动类")
  private String driverClass;

  @ApiModelProperty(value = "最小连接池大小")
  private int minPoolSize;

  @ApiModelProperty(value = "最大连接池大小")
  private int maxPoolSize;

  @ApiModelProperty(value = "最大空闲时间")
  private Long maxIdleTime;

  @ApiModelProperty(value = "空闲连接测试周期")
  private Long idleConnectionTestPeriod;

}
