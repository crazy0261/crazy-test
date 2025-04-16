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
 * 场景用例执行结果
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("process_case_result")
@ApiModel(value = "ProcessCaseResult对象", description = "场景用例执行结果")
public class ProcessCaseResult implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "应用Id")
  private Long appId;

  @ApiModelProperty(value = "任务Id")
  private Long scheduleId;

  @ApiModelProperty(value = "定时任务批次Id")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "用例id")
  private Long caseId;

  @ApiModelProperty(value = "节点")
  private String nodes;

  @ApiModelProperty(value = "边")
  private String edges;

  @ApiModelProperty(value = "执行环境id")
  private Long envNameId;

  @ApiModelProperty(value = "执行次数")
  private Integer execTimes;

  @ApiModelProperty(value = "状态")
  private String status;

  @ApiModelProperty(value = "用例入参")
  private String inputParams;

  @ApiModelProperty(value = "用例出参")
  private String outputParams;

  @ApiModelProperty(value = "模式")
  private String mode;

  @ApiModelProperty(value = "创建者ID")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建者姓名")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者ID")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改者姓名")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "更新时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;

}
