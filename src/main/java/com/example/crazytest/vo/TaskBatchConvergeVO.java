package com.example.crazytest.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/24 21:03
 * @DESRIPTION
 */

@Data
public class TaskBatchConvergeVO {

  @ApiModelProperty(value = "任务ID")
  private Long scheduleId;

  @ApiModelProperty(value = "任务名称")
  private String scheduleName;

  @ApiModelProperty(value = "批量任务ID")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "环境ID")
  private Integer envSortId;

  @ApiModelProperty(value = "状态")
  private String status;

  @ApiModelProperty(value = "总数")
  private Long totalCount;

  @ApiModelProperty(value = "成功数")
  private Long successCount;

  @ApiModelProperty(value = "失败数")
  private Long failCount;

  @ApiModelProperty(value = "运行中数")
  private Long runningCount;

  @ApiModelProperty(value = "初始化数")
  private Long initCount;

  @ApiModelProperty(value = "忽略数")
  private Long ignoreCount;

  @ApiModelProperty(value = "超时数")
  private Long timeoutCount;

}
