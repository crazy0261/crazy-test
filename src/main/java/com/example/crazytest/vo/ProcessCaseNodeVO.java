package com.example.crazytest.vo;

import com.example.crazytest.entity.ProcessCaseNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/13 21:10
 * @DESRIPTION
 */

@Data
public class ProcessCaseNodeVO extends ProcessCaseNode {

  @ApiModelProperty(value = "节点名称")
  private String name;

}
