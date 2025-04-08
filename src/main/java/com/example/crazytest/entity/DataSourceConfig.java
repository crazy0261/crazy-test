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
 * 数据库配置
 * </p>
 *
 * @author Menghui
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("data_source_config")
@ApiModel(value = "DataSourceConfig对象", description = "数据库配置")
public class DataSourceConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "名称")
  private String Name;

  @ApiModelProperty(value = "环境id")
  private Long envId;

  @ApiModelProperty(value = "数据库名称")
  private String dbName;

  @ApiModelProperty(value = "数据库连接URL")
  private String url;

  @ApiModelProperty(value = "数据库账号")
  private String username;

  @ApiModelProperty(value = "数据库密码")
  private String password;

  @ApiModelProperty(value = "数据库驱动类")
  private String driverClassName;

  @ApiModelProperty(value = "最小连接池大小")
  private int minPoolSize;

  @ApiModelProperty(value = "最大连接池大小")
  private int maxPoolSize;

  @ApiModelProperty(value = "最大空闲时间")
  private Long maxIdleTime;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "空闲连接测试周期")
  private Long idleConnectionTestPeriod;

  @ApiModelProperty(value = "创建者id")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建人")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者ID")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改人")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "是否删除")
  @TableLogic
  private Long isDelete;


}
