package com.example.crazytest.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 22:56
 * @DESRIPTION
 */

public class CUrlUtil {

  public static Map<String, Object> parse(String curlCommand) {
    Map<String, Object> result = new HashMap<>();
    List<String> headerFilters = Arrays.asList(
        "Accept",
        "Accept-Language",
        "Connection",
        "Referer",
        "Sec-Fetch-Dest",
        "Sec-Fetch-Mode",
        "Sec-Fetch-Site",
        "User-Agent",
        "sec-ch-ua",
        "sec-ch-ua-mobile",
        "sec-ch-ua-platform",
        "Origin"
    );

    String url = null;
    String method = "GET"; // 默认请求方式为 GET
    Map<String, String> headers = new HashMap<>();
    Map<String, String> queryParams = new HashMap<>();
    String body = null;
    // 正则表达式提取 URL
    Pattern urlPattern = Pattern.compile("\\s+'([^']+)'");
    Matcher urlMatcher = urlPattern.matcher(curlCommand);
    if (urlMatcher.find()) {
      url = urlMatcher.group(1);
      // 提取 URL 中的查询参数
      int queryStart = url.indexOf('?');
      if (queryStart != -1) {
        String queryString = url.substring(queryStart + 1);
        url = url.substring(0, queryStart);
        for (String param : queryString.split("&")) {
          String[] keyValue = param.split("=");
          if (keyValue.length == 2) {
            queryParams.put(keyValue[0], keyValue[1]);
          }
        }
      }
      int firstSlash = url.indexOf('/', 7); // 从第 7 个字符开始查找第一个斜杠
      int secondSlash = url.indexOf('/', firstSlash + 1);
      url = url.substring(secondSlash);
    }

    // 提取请求头
    Pattern headerPattern = Pattern.compile("-H\\s+'([^:]+):\\s+([^']+)'");
    Matcher headerMatcher = headerPattern.matcher(curlCommand);
    while (headerMatcher.find()) {
      if (!headerFilters.contains(headerMatcher.group(1))) {
        headers.put(headerMatcher.group(1), headerMatcher.group(2));
      }
    }
    // 提取请求体
    Pattern bodyPattern = Pattern.compile("--data-raw\\s+'([^']+)'");
    Matcher bodyMatcher = bodyPattern.matcher(curlCommand);
    if (bodyMatcher.find()) {
      method = "POST"; // 如果有请求体，默认请求方式为 POST
      body = bodyMatcher.group(1);
    }
    // 提取请求方式（如果有明确的请求方式）
    Pattern methodPattern = Pattern.compile("-X\\s+([^\\s]+)");
    Matcher methodMatcher = methodPattern.matcher(curlCommand);
    if (methodMatcher.find()) {
      method = methodMatcher.group(1).toUpperCase();
    }
    // 填充结果
    result.put("url", url);
    result.put("method", method);
    result.put("headers", headers);
    result.put("queryParams", queryParams);
    result.put("body", body);
    return result;
  }
}

