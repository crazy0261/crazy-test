package com.example.crazytest.convert;

import com.alibaba.fastjson.JSONArray;
import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @name Menghui
 * @date 2025/4/20 09:56
 * @DESRIPTION
 */

public class mapArrayConvert {


  /**
   * 将nodeMap转换为JSONArray
   * @param nodeMap
   * @return
   */
  public static JSONArray nodeMapToJsonArray(Map<String, Node> nodeMap) {

    JSONArray jsonArray = new JSONArray();
    jsonArray.addAll(nodeMap.values());
    return jsonArray;
  }

  /**
   * 将edgeMap转换为JSONArray
   * @param edgeMap
   * @return
   */
  public static JSONArray edgeMapToJsonArray(Map<String, List<Edge>> edgeMap) {
    JSONArray jsonArray = new JSONArray();
    for (List<Edge> edges : edgeMap.values()) {
      jsonArray.addAll(edges);
    }
    return jsonArray;
  }



}
