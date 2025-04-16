package com.example.crazytest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @author
 * @name Menghui
 * @date 2025/4/2 21:07
 * @DESRIPTION json path 格式校验 获取值
 */

public class JSONPathUtil {

  private JSONPathUtil() {
  }

  /**
   * 判断jsonPath是否是jsonPath校验
   *
   * @param jsonPath
   * @return
   */
  public static boolean isJsonPathCheck(String jsonPath) {
    if (StringUtils.isEmpty(jsonPath) && !jsonPath.startsWith("$.")) {
      return false;
    }
    if (jsonPath.contains("size()")) {
      return isJsonPathSizeCheck(jsonPath);
    }
    return isJsonPathFormatCheck(jsonPath);
  }

  /**
   * 判断jsonPath是否是jsonPath 及返回值
   *
   * @param body
   * @param jsonPath
   * @return
   */
  public static String isJsonPathValue(JSONObject body, String jsonPath) {

    return Optional.ofNullable(jsonPath).filter(JSONPathUtil::isJsonPathCheck)
        .map(path -> path.endsWith(".size()") ? Objects
            .requireNonNull(getJsonPathSizeValue(body, jsonPath)).toString()
            : JSONPath.eval(body, jsonPath).toString())
        .orElse("");
  }

  /**
   * 判断jsonPath是否是size()校验
   *
   * @param jsonPath
   * @return
   */
  public static boolean isJsonPathSizeCheck(String jsonPath) {
    String regex = "^(.*)\\.size\\(\\)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(jsonPath);

    if (matcher.matches()) {
      String path = matcher.group(1);
      return isJsonPathFormatCheck(path);
    }
    return false;
  }


  /**
   * 判断jsonPath格式是否正确
   *
   * @param jsonPath
   * @return
   */
  public static boolean isJsonPathFormatCheck(String jsonPath) {
    try {
      JSONPath.compile(jsonPath);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 获取jsonPath的值
   *
   * @param body
   * @param jsonPath
   * @return
   */
  public static Object getJsonPathSizeValue(JSONObject body, String jsonPath) {
    try {
      String path = jsonPath.replaceAll("\\.size\\(\\)$", "");
      JSONArray jsonArray = JSON.parseArray(JSONPath.eval(body, path).toString());
      return String.valueOf(jsonArray.size());
    } catch (Exception e) {
      return null;
    }
  }

}
