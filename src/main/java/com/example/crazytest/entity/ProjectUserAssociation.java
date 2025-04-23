package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目用户关联表
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_user_association")
@ApiModel(value = "ProjectUserAssociation对象", description = "项目用户关联表")
public class ProjectUserAssociation implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "用户id")
  private Long userId;

  @ApiModelProperty(value = "状态 0启用 1停用")
  private Integer status;

  @ApiModelProperty(value = "删除 0未删除 1删除")
  private Integer isDelete;

  @ApiModelProperty(value = "创建人id")
  @TableField(fill = FieldFill.INSERT)
  private Integer createById;

  @ApiModelProperty(value = "创建人姓名")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改人id")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Integer updateById;

  @ApiModelProperty(value = "修改人姓名")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

}
