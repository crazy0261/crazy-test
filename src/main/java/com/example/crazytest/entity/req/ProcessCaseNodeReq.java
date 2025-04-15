package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.vo.AssertVO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/13 17:09
 * @DESRIPTION
 */

@Data
public class ProcessCaseNodeReq {

  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "接口id")
  private Long apiId;

  @ApiModelProperty(value = "用例id")
  private Long caseId;

  @ApiModelProperty(value = "子流程用例id")
  private Long subCaseId;

  @ApiModelProperty(value = "请求头")
  private String requestHeaders;

  @ApiModelProperty(value = "请求参数")
  private String requestParams;

  @ApiModelProperty(value = "断言")
  List<AssertVO> assertsArray;

  @ApiModelProperty(value = "入参")
  private String inputParams;

  @ApiModelProperty(value = "节点出参")
  private String outputParams;

  @ApiModelProperty(value = "node节点")
  private List<JSONObject> nodes;

  @ApiModelProperty(value = "边")
  private List<JSONObject> edges;

  @ApiModelProperty(value = "条件节点和前置步骤输出参数的key")
  private String groovyKey;

  @ApiModelProperty(value = "groovy脚本(仅条件节点用到)")
  private String groovyScript;

  @ApiModelProperty(value = "加密参数id")
  private Long secretId;

  @ApiModelProperty(value = "数据源ID(仅SQL节点用到)")
  private Long dataSourceId;

  @ApiModelProperty(value = "SQL脚本(仅SQL节点用到)")
  private String sqlScript;

}
