package com.example.crazytest.vo;

import com.example.crazytest.entity.TaskSchedule;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 16:19
 * @DESRIPTION
 */

@Data
public class TaskScheduleVO extends TaskSchedule {

  @ApiModelProperty(value = "负责人")
  private String ownerName;

  @ApiModelProperty(value = "是否全选")
  private Boolean isAllCase;

  @ApiModelProperty(value = "用例列表")
  private List<Long> apiCaseIds;

}
