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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("api_case_record")
@ApiModel(value = "ApiCaseRecord对象", description = "接口执行结果")
public class ApiCaseRecord implements Serializable {

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

  @ApiModelProperty(value = "用例id")
  private Long apiTestcaseId;

  @ApiModelProperty(value = "用例负责人ID")
  private Long caseOwnerId;

  @ApiModelProperty(value = "环境id")
  private Long envId;

  @ApiModelProperty(value = "执行模式：自动 schedule/手动 manual")
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

  @ApiModelProperty(value = "是否删除")
  private Long isDelete;

}
