package com.example.crazytest.entity.req;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/12 23:25
 * @DESRIPTION
 */

@Data
public class UserResultEntity {

  private Long id;

  @ApiModelProperty(value = "租户")
  private Long projectId;

  @ApiModelProperty(value = "账号")
  private String account;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "项目id")
  private Long selectProject;

  @ApiModelProperty(value = "角色id")
  private Long roleId;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "状态")
  private Boolean status;

  @ApiModelProperty(value = "创建者")
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者")
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  private LocalDateTime updateTime;

}
