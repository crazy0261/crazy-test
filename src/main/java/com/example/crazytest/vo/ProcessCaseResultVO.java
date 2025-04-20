package com.example.crazytest.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/20 02:17
 * @DESRIPTION
 */

@Data
public class ProcessCaseResultVO {

  private Long id;
  private Long resultId;
  private String status;
  private String caseName;
  private Long envSortId;
  JSONArray nodeArray;
  JSONArray edgeArray;
  String inputParams;
  String outputParams;

}
