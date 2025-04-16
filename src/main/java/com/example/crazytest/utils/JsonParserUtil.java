package com.example.crazytest.utils;

import com.example.crazytest.entity.Edge;
import com.example.crazytest.entity.Node;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:32
 * @DESRIPTION node和边转换工具
 */

@Slf4j
@Component
public class JsonParserUtil {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public List<Node> parseNodes(String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<List<Node>>() {});
    } catch (Exception e) {
      log.error("Failed to parse nodes JSON", e);
      throw new RuntimeException("node转换异常：", e);
    }
  }

  public List<Edge> parseEdges(String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<List<Edge>>() {});
    } catch (Exception e) {
      log.error("转换边", e);
      throw new RuntimeException("edge边转换异常：", e);
    }
  }
}
