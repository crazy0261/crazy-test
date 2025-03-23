package com.example.crazytest.vo;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 22:26
 * @DESRIPTION
 */

@Data
public class TestAccountVO {

  private Long id;

  @ApiModelProperty(value = "环境id")
  private Long envId;

  @ApiModelProperty(value = "环境id")
  private String envName;

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "测试账号")
  private String account;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "接口用例id")
  private Long apiCaseId;

  @ApiModelProperty(value = "接口用例名称")
  private String apiCaseName;

  @ApiModelProperty(value = "token")
  private String token;

  @ApiModelProperty(value = "生成token状态")
  private String genTokenStatus;

  @ApiModelProperty(value = "失败原因")
  private String failReason;

  @ApiModelProperty(value = "创建者ID")
  private Long createById;

  @ApiModelProperty(value = "创建者")
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者ID")
  private Long updateById;

  @ApiModelProperty(value = "修改者")
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  private LocalDateTime updateTime;

}
