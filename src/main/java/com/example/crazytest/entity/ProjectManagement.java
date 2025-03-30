package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 项目管理
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_management")
@ApiModel(value = "ProjectManagement对象", description = "项目管理")
public class ProjectManagement implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "项目名称")
  private String name;

  @ApiModelProperty(value = "项目描述")
  private String remark;

  @ApiModelProperty(value = "创建者id")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建者")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者id")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "创建者")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "是否删除")
  @TableLogic
  private Integer isDelete;


}
