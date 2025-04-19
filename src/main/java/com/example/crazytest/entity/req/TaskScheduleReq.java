package com.example.crazytest.entity.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/19 20:14
 * @DESRIPTION
 */

@Data
public class TaskScheduleReq {

  @ApiModelProperty(value = "主键id")
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "环境顺序")
  private Integer envSort;

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
  private List<Long> testcaseList;

}
