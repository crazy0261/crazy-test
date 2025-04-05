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
 * 应用管理
 * </p>
 *
 * @author Menghui
 * @since 2025-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("application_info")
@ApiModel(value = "ApplicationManagement对象", description = "应用管理")
public class ApplicationManagement implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "应用名")
  private String name;

  @ApiModelProperty(value = "应用描述")
  private String remark;

  @ApiModelProperty(value = "负责人")
  private Long ownerId;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者id")
  private Long createById;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者")
  private String createByName;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "修改者id")
  private Long updateById;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新者")
  private String updateByName;


  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;


}
