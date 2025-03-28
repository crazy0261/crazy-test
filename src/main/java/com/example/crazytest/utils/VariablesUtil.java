package com.example.crazytest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.vo.ParamsListVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/24 21:44
 * @DESRIPTION
 */

@Service
public class VariablesUtil {

  public Map<String, String> formatHeader(String envId, Map<String, String> variables,
      List<ParamsListVO> paramsArrList, EnvConfig envConfig, ApiCase apiCase) {
    List<ParamsListVO> headerEnv = Optional.ofNullable(envConfig)
        .map(item -> JSON.parseArray(item.getRequestHeaders(), ParamsListVO.class))
        .orElse(new ArrayList<>());
    List<ParamsListVO> envVariables = Optional.ofNullable(envConfig)
        .map(item -> JSON.parseArray(item.getEnvVariables(), ParamsListVO.class))
        .orElse(new ArrayList<>());
    List<ParamsListVO> apiCaseVariables = Optional.ofNullable(apiCase)
        .map(item -> JSON.parseObject(item.getEnvVariables()))
        .map(item -> item.getJSONObject(envId))
        .map(item -> item.getJSONArray("envVariables"))
        .map(item -> item.toJavaList(ParamsListVO.class))
        .orElse(new ArrayList<>());


    // 合并变量
    Map<String, String> allVariables = Stream
        .concat(envVariables.stream(), apiCaseVariables.stream())
        .collect(Collectors.toMap(
            ParamsListVO::getKey,
            ParamsListVO::getValue,
            (oldValue, newValue) -> newValue
        ));

    // 自定义请求参数
    Map<String, String> paramsMap = paramsArrList.stream().collect(Collectors.toMap(
        ParamsListVO::getKey,
        ParamsListVO::getValue,
        (oldValue, newValue) -> newValue
    ));

    allVariables.putAll(variables);
    allVariables.putAll(paramsMap);

    // 请求头
    return headerEnv.stream()
        .collect(Collectors.toMap(
            ParamsListVO::getKey,
            item -> replaceStr(item.getValue(), allVariables),
            (oldValue, newValue) -> newValue));
  }


  // 替换字符串
  private String replaceStr(String value, Map<String, String> variables) {
    if (Objects.isNull(value)) {
      return "";
    }

    // 正则匹配
    Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    Matcher matcher = pattern.matcher(value);

    if (!matcher.find()) {
      return value;
    }

    String variablesValue = variables.getOrDefault(matcher.group(1), "");
    return value.replaceAll("\\$\\{([^}]+)}", variablesValue);
  }


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
