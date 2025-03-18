package com.example.crazytest.entity.req;

import com.example.crazytest.utils.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/17 21:17
 * @DESRIPTION
 */

@Data
public class ApplicationManagementReq extends PageParam {

  private Long id;

  @ApiModelProperty(value = "应用名")
  private String name;

  @ApiModelProperty(value = "负责人")
  private Long ownerId;

  @ApiModelProperty(value = "应用描述")
  private String  remark;

  @ApiModelProperty(value = "租户")
  private String tenantId;


}
