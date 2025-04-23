package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/9 21:55
 * @DESRIPTION
 */

@Data
public class EncryptInfoReq {

  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "加密信息")
  private JSONArray encryptJson;


}
