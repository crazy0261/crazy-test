package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
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

  @ApiModelProperty(value = "数据源名称")
  private String name;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "数据库名")
  private String dbName;

  @ApiModelProperty(value = "数据库连接URL")
  private String jdbcUrl;

  @ApiModelProperty(value = "数据库账号")
  private String username;

  @ApiModelProperty(value = "数据库密码")
  private String password;

  @ApiModelProperty(value = "数据库驱动类")
  private String driverClass = "com.mysql.cj.jdbc.Driver";

  @ApiModelProperty(value = "最小连接池大小")
  private int minPoolSize = 1;

  @ApiModelProperty(value = "最大连接池大小")
  private int maxPoolSize = 10;

  @ApiModelProperty(value = "最大空闲时间")
  private Long maxIdleTime = 20000L;

  @ApiModelProperty(value = "空闲连接测试周期")
  private Long idleConnectionTestPeriod = 3600L;

  @ApiModelProperty(value = "数据库连接信息")
  ArrayList<JSONObject> dataSourceJson;

  @ApiModelProperty(value = "备注")
  private String remark;

}
