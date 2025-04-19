package com.example.crazytest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.vo.ParamsListVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/24 21:44
 * @DESRIPTION
 */

@Service
public class VariablesUtil {

  public Map<String, String> formatHeader(Map<String, String> variables, EnvConfig envConfig,
      String requestHeader) {
    List<ParamsListVO> headerEnv = Optional.ofNullable(envConfig)
        .map(item -> JSON.parseArray(item.getRequestHeaders(), ParamsListVO.class))
        .orElse(new ArrayList<>());

    // 应用 请求头
    Map<String, String> envConfigMap = Optional.of(headerEnv).map(List::stream)
        .orElseGet(Stream::empty)
        .collect(Collectors.toMap(ParamsListVO::getKey, ParamsListVO::getValue));

    // 请求中请求头
    Map<String, String> headerMap = Optional.ofNullable(requestHeader)
        .map(item -> JSON.parseObject(item, new TypeReference<Map<String, String>>() {
        }))
        .orElse(new HashMap<>());

    envConfigMap.putAll(headerMap);
    envConfigMap.replaceAll((key, value) -> replaceStr(value, variables));

    return envConfigMap;
  }


  // 替换字符串 ${xxx} 为变量值 xxx
  private static String replaceStr(String value, Map<String, String> variables) {
    if (Objects.isNull(value) || Objects.isNull(variables)) {
      return value;
    }

    // 正则匹配
    Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    Matcher matcher = pattern.matcher(value);

    if (!matcher.find()) {
      return value;
    }

    String variablesValue = variables.getOrDefault(matcher.group(1), "");
    return value.replaceAll("\\$\\{".concat(matcher.group(1)).concat("}"), variablesValue);
  }

  /**
   * 替换字符串 ${xxx} 为变量值 xxx 替换多个 批量
   *
   * @param value
   * @param variables
   * @return
   */
  public static String replacePlaceholders(String value, Map<String, String> variables) {
    StringBuilder result = new StringBuilder();

    if (Objects.isNull(value) || Objects.isNull(variables)) {
      return value;
    }

    Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    Matcher matcher = pattern.matcher(value);

    int lastIndex = 0;
    while (matcher.find()) {
      result.append(value, lastIndex, matcher.start());
      String beforeReplacement = matcher.group(1);
      String afterReplacement = variables.getOrDefault(beforeReplacement, "");
      result.append(afterReplacement);

      lastIndex = matcher.end();
    }
    result.append(value, lastIndex, value.length());
    return result.toString();
  }


  /**
   * 请求参数替换
   *
   * @param requestParam
   * @param variables
   * @return
   */
  public JSONObject formatParams(String requestParam, Map<String, String> variables) {
    JSONObject jsonObject = JSON.parseObject(requestParam);
    replaceParamStr(jsonObject, variables);
    return jsonObject;
  }

  // 处理请求参数嵌套替换
  public void replaceParamStr(Object json, Map<String, String> variables) {
    if (json instanceof JSONObject) {
      JSONObject jsonObject = (JSONObject) json;
      for (String key : jsonObject.keySet()) {
        Object value = jsonObject.get(key);
        if (value instanceof JSONObject || value instanceof JSONArray) {
          replaceParamStr(value, variables); // 递归处理嵌套对象或数组
        } else if (value instanceof String) {
          jsonObject.put(key, replaceStr(value.toString(), variables)); // 替换字符串
        }
      }
    } else if (json instanceof JSONArray) {
      JSONArray jsonArray = (JSONArray) json;
      for (int i = 0; i < jsonArray.size(); i++) {
        Object value = jsonArray.get(i);
        if (value instanceof JSONObject || value instanceof JSONArray) {
          replaceParamStr(value, variables); // 递归处理嵌套对象或数组
        } else if (value instanceof String) {
          jsonArray.set(i, replaceStr(value.toString(), variables)); // 替换字符串
        }
      }
    }
  }

}
