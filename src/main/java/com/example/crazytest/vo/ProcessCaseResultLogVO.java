package com.example.crazytest.vo;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/24 23:38
 * @DESRIPTION
 */

@Data
public class ProcessCaseResultLogVO {

  private String id;

  @ApiModelProperty(value = "定时任务批次id")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "执行状态,INIT,RUNNING,SUCCESS,FAIL")
  private String status;

  @ApiModelProperty(value = "执行环境")
  private String envName;

  @ApiModelProperty(value = "执行结果")
  private String debugResult;

  @ApiModelProperty(value = "执行时间")
  private LocalDateTime createTime;
}
