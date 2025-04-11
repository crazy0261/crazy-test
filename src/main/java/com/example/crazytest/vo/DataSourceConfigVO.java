package com.example.crazytest.vo;

import com.example.crazytest.entity.DataSourceConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/11 23:36
 * @DESRIPTION
 */
@Data
public class DataSourceConfigVO extends DataSourceConfig {

  @ApiModelProperty(value = "应用名称")
  private String appName;

}
