package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/12 20:31
 * @DESRIPTION
 */

@Data
public class ProcessCaseReq {

  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "树节点")
  private String treeKey;

  @ApiModelProperty(value = "用例id")
  private Long caseId;

  @ApiModelProperty(value = "节点名称")
  private String name;

  @ApiModelProperty(value = "优先级")
  private Integer priority;

  @ApiModelProperty(value = "是否是子流程")
  private Integer isSubProcess;

  @ApiModelProperty(value = "入参")
  private JSONObject inputParams;

  @ApiModelProperty(value = "子流程用例id")
  private List<JSONObject> nodes;

  @ApiModelProperty(value = "子流程用例id")
  private List<JSONObject> edges;

  @ApiModelProperty(value = "备注")
  private String remark;

}
