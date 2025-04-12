package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/23 14:31
 * @DESRIPTION
 */

@Data
public class ApiDebugReq {

  private Long id;
  private Long envId;
  private Long testAccount;
  private JSONArray inputParams;
  private String mode;
  private String remark;
  private Long scheduleId;
  private Long scheduleBatchId;
}
