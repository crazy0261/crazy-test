package com.example.crazytest.entity.req;

import com.example.crazytest.utils.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 20:16
 * @DESRIPTION
 */

@Data
public class ProjectManagementReq extends PageParam {

  long id;

  @ApiModelProperty(value = "项目名称")
  private String name;

}
