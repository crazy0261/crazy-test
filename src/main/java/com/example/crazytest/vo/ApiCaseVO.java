package com.example.crazytest.vo;

import com.example.crazytest.entity.ApiCase;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 15:04
 * @DESRIPTION
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiCaseVO extends ApiCase {

  @ApiModelProperty(value = "应用名称")
  private String appName;

  @ApiModelProperty(value = "接口路径")
  private String path;

  @ApiModelProperty(value = "域名")
  private String domainUrl;

  @ApiModelProperty(value = "接口负责人")
  private String ownerName;

  @ApiModelProperty(value = "断言")
  public List<AssertReqVo> assertsArray;

}
