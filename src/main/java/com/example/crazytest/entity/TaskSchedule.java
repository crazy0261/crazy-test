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
 * 定时任务
 * </p>
 *
 * @author Menghui
 * @since 2025-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("task_schedule")
@ApiModel(value = "TaskSchedule对象", description = "定时任务")
public class TaskSchedule implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "主键id")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "项目id")
  private String projectId;

  @ApiModelProperty(value = "环境变量")
  private String env;

  @ApiModelProperty(value = "执行模式")
  private String execMode;

  @ApiModelProperty(value = "定时任务名称")
  private String name;

  @ApiModelProperty(value = "定时任务描述")
  private String remark;

  @ApiModelProperty(value = "cron表达式")
  private String cron;

  @ApiModelProperty(value = "状态，0启用，1禁用")
  private Boolean enable;

  @ApiModelProperty(value = "执行状态")
  private String status;

  @ApiModelProperty(value = "负责人")
  private Long ownerId;

  @ApiModelProperty(value = "用例类型：接口用例、场景用例")
  private String testcaseType;

  @ApiModelProperty(value = "用例列表")
  private String testcaseList;

  @ApiModelProperty(value = "创建人id")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建人")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "最后修改人id")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改人")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "删除")
  @TableLogic
  private Long isDelete;


}
