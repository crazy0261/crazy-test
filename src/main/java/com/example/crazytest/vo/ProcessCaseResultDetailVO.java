package com.example.crazytest.vo;

import com.alibaba.fastjson.JSONArray;
import com.example.crazytest.entity.ProcessCaseRecord;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/23 16:57
 * @DESRIPTION
 */

@Data
public class ProcessCaseResultDetailVO extends ProcessCaseRecord {

  @ApiModelProperty(value = "节点")
  JSONArray nodeArray;

  @ApiModelProperty(value = "边")
  JSONArray edgeArray;

  @ApiModelProperty(value = "用例名称")
  private String caseName;

  @ApiModelProperty(value = "负责人姓名")
  private String ownerName;

  @ApiModelProperty(value = "子结果")
  private List<ProcessCaseResultDetailVO> children;

  @ApiModelProperty(value = "环境名称")
  private String envName;

  @ApiModelProperty(value = "用例类型")
  private String caseType;

}
