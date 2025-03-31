package com.example.crazytest.entity.req;

import com.example.crazytest.vo.AssertVO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 14:49
 * @DESRIPTION
 */

@Data
public class ApiCaseReq {

  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "关联的接口id")
  private Long apiId;

  @ApiModelProperty(value = "用例名")
  private String name;

  @ApiModelProperty(value = "负责人id")
  private Long ownerId;

  @ApiModelProperty(value = "接口路径")
  private String urlPath;

  @ApiModelProperty(value = "优先级")
  private Integer priority;

  @ApiModelProperty(value = "用例变量，优先级高于环境变量")
  private String envVariables;

  @ApiModelProperty(value = "请求头")
  private String requestHeaders;

  @ApiModelProperty(value = "请求参数")
  private String requestParams;

  @ApiModelProperty(value = "断言")
  private List<AssertVO> assertsArray;

}
