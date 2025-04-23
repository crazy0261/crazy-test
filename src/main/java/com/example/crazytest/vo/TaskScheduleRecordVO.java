package com.example.crazytest.vo;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/13 22:43
 * @DESRIPTION
 */

@Data
public class TaskScheduleRecordVO {

  @ApiModelProperty(value = "定时任务Id")
  private Long scheduleId;

  @ApiModelProperty(value = "定时任务名称")
  private String scheduleName;

  @ApiModelProperty(value = "定时任务批次Id")
  private String scheduleBatchId;

  @ApiModelProperty(value = "环境名称")
  private String envName;

  @ApiModelProperty(value = "状态")
  private String status;

  @ApiModelProperty(value = "执行时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "创建人")
  private String createByName;

}
