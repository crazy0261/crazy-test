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
 * 环境信息表
 * </p>
 *
 * @author Menghui
 * @since 2025-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("env_config")
@ApiModel(value = "EnvConfig对象", description = "环境信息表")
public class EnvConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "环境名")
  private String name;

  @ApiModelProperty(value = "域名id")
  private Long domainId;

  @ApiModelProperty(value = "请求头")
  private String requestHeaders;

  @ApiModelProperty(value = "环境变量")
  private String envVariables;

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
