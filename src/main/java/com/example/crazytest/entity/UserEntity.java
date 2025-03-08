package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author
 * @name Menghui
 * @date 2025/3/7 22:10
 * @DESRIPTION
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户id")
  private String tenantId;

  @ApiModelProperty(value = "账号")
  private String account;

  @ApiModelProperty(value = "用户名")
  private String name;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "项目id")
  private String selectProject;

  @ApiModelProperty(value = "角色id")
  private Long roleId;

  @ApiModelProperty(value = "状态")
  private Boolean status;

  @ApiModelProperty(value = "是否删除")
  private Boolean isDelete;

  @ApiModelProperty(value = "创建人id")
  private Long createById;

  @ApiModelProperty(value = "创建人名称")
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改人id")
  private Long updateById;

  @ApiModelProperty(value = "修改人名称")
  private String updateByName;

  @ApiModelProperty(value = "修改时间时间")
  private LocalDateTime updateTime;

}
