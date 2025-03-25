package com.example.crazytest.vo;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/25 20:44
 * @DESRIPTION
 */

@Data
@Builder
public class ResultApiVO {

  private JSONObject response;
  private AssertResultVo assertResultVo;
  private Map<String, String> requestHeaders;
  private Object requestParams;
  private String requestUrl;
  private String startExecTime; // 开始执行时间
  private Long execTime;  // 执行耗时（ms）
//  private List<AssertVo> autoGenerateAsserts;  // 自动生成的断言
  private Long envNameId;
  private String envName;


}
