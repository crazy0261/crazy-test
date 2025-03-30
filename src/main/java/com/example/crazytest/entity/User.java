package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户id")
  private String tenantId;

  @ApiModelProperty(value = "账号")
  private String account;

  @ApiModelProperty(value = "姓名")
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

  @ApiModelProperty(value = "创建人id")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建人名称")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改人id")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改人名称")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "是否删除")
  private Boolean isDelete;

}
