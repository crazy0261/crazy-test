package com.example.crazytest.dto;

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
public class ApiManagementVo extends ApiManagement {

  @ApiModelProperty(value = "应用名称")
  private String applicationName;

  @ApiModelProperty(value = "负责人id")
  private Long ownerById;

  @ApiModelProperty(value = "负责人名称")
  private String ownerByName;

  @ApiModelProperty(value = "创建者id")
  private Long createById;

  @ApiModelProperty(value = "创建者姓名")
  private String createByName;

  @ApiModelProperty(value = "修改者id")
  private Long updateById;

  @ApiModelProperty(value = "修改者姓名")
  private String updateByName;
}
