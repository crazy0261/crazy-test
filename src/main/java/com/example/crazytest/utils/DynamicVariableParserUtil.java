package com.example.crazytest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author
 * @name Menghui
 * @date 2025/4/10 22:02
 * @DESRIPTION 公共变量解析工具类
 */

public class DynamicVariableParserUtil {

  private static final Map<String, Supplier<String>> dynamicVariables = new HashMap<>();

  static {
    // 初始化动态变量
    dynamicVariables.put("__timestamp__", () -> String.valueOf(System.currentTimeMillis()));
    dynamicVariables
        .put("__timestamp_second__", () -> String.valueOf(System.currentTimeMillis() / 1000));
    dynamicVariables
        .put("__current_time__", () -> new SimpleDateFormat("HH:mm:ss").format(new Date()));
    dynamicVariables
        .put("__current_date__", () -> new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
  }

  private DynamicVariableParserUtil() {
    // 私有构造函数，防止实例化
  }

  public static Map<String, String> parseToMap() {
    Map<String, String> resultMap = new HashMap<>();
    for (Map.Entry<String, Supplier<String>> entry : dynamicVariables.entrySet()) {
      resultMap.put(entry.getKey(), entry.getValue().get());
    }
    return resultMap;
  }

}
