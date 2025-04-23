package com.example.crazytest.vo;

import com.example.crazytest.entity.ApiManagement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 10:55
 * @DESRIPTION
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiManagementVO extends ApiManagement {

  @ApiModelProperty(value = "应用名称")
  private String applicationName;

  @ApiModelProperty(value = "负责人名")
  private String ownerName;
}
