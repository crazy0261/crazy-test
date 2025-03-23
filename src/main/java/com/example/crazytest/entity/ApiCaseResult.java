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
 * 接口执行结果
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api_case_result")
@ApiModel(value = "ApiCaseResult对象", description = "接口执行结果")
public class ApiCaseResult implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "主键id")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "项目id")
  private String projectId;

  @ApiModelProperty(value = "定时任务id")
  private Long scheduleId;

  @ApiModelProperty(value = "定时任务批次id")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "用例id")
  private String apiTestcaseId;

  @ApiModelProperty(value = "用例负责人ID")
  private Long caseOwnerId;

  @ApiModelProperty(value = "环境名称id")
  private String envNameId;

  @ApiModelProperty(value = "执行模式,schedule")
  private String mode;

  @ApiModelProperty(value = "执行状态,INIT,RUNNING,SUCCESS,FAIL")
  private String status;

  @ApiModelProperty(value = "执行次数")
  private Integer execTimes;

  @ApiModelProperty(value = "执行结果")
  private String debugResult;

  @ApiModelProperty(value = "创建者id")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建人名称")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者id")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改者名称")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "修改时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

}
