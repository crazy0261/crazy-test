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
 * 定时任务执行记录表
 * </p>
 *
 * @author Menghui
 * @since 2025-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("task_schedule_record")
@ApiModel(value = "TaskScheduleRecord对象", description = "定时任务执行记录表")
public class TaskScheduleRecord implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "主键id")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "定时任务id")
  private Long scheduleId;

  @ApiModelProperty(value = "定时任务批次id")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "环境id")
  private Integer envSortId;

  @ApiModelProperty(value = "执行模式")
  private String mode;

  @ApiModelProperty(value = "执行状态")
  private String status;

  @ApiModelProperty(value = "用例类型")
  private String testcaseType;

  @ApiModelProperty(value = "用例列表")
  private String testcaseList;

  private String ip;

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
  private Long isDelete;

}
