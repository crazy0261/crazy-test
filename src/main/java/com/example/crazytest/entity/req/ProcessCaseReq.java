package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 15:16
 * @DESRIPTION
 */

@Data
public class ProcessCaseReq {

  private Long id;
  private String treeKey;
  private String name;
  private Integer priority;
  private Integer isSubProcess;
  List<JSONObject> nodes;
  List<JSONObject> edges;


}
