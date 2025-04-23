package com.example.crazytest.vo;

import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.entity.ProcessCase;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 13:30
 * @DESRIPTION
 */

@Data
public class ProcessCaseVO extends ProcessCase {

  @ApiModelProperty(value = "入参")
  private JSONObject inputParamsJson;

  @ApiModelProperty(value = "节点")
  private List<JSONObject> nodeArray;

  @ApiModelProperty(value = "边")
  private List<JSONObject> edgesArray;

  @ApiModelProperty(value = "优先级描述")
  private String priorityDesc;

  @ApiModelProperty(value = "负责人姓名")
  private String ownerName;

  @ApiModelProperty(value = "最近一次执行结果")
  private String recentExecResult;

  @ApiModelProperty(value = "最近一次执行时间")
  private LocalDateTime recentExecTime;

}
