package com.example.crazytest.vo;

import com.example.crazytest.entity.ApiCaseRecord;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/13 23:47
 * @DESRIPTION
 */

@Data
public class ApiCaseResultVO extends ApiCaseRecord {

  @ApiModelProperty(value = "用例名称")
  private String caseName;

  @ApiModelProperty(value = "用例负责人名称")
  private String ownerName;

  @ApiModelProperty(value = "子任务")
  private List<ApiCaseResultVO> children;

  @ApiModelProperty(value = "环境名称")
  private String envName;

  @ApiModelProperty(value = "用例类型")
  private String caseType;

}
