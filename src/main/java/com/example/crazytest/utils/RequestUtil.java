package com.example.crazytest.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.config.OkHttpRequestConfig;
import com.example.crazytest.factory.OkHttpClientFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author
 * @name Menghui
 * @date 2025/3/19 20:32
 * @DESRIPTION
 */

public class RequestUtil {

  public static String sendRequest(OkHttpRequestConfig request) {
    // 创建 OkHttpClient 实例
    OkHttpClient client = OkHttpClientFactory.createClient(
        request.getConnectTimeout(),
        request.getReadTimeout(),
        request.getWriteTimeout()
    );
    // 判断请求方式是否包含
    String url = replacePathVariables(request.getUrl(), request.getParams());

    // 构建请求
    Request.Builder requestBuilder = new Request.Builder();
    // 根据请求方式构建 URL 和请求体
    if (request.getMethod().equalsIgnoreCase("GET")) {

      HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

      Map<String, String> params = flattenJson(
          Optional.ofNullable(request.getParams()).orElse(new JSONObject()));

      for (Map.Entry<String, String> entry : params.entrySet()) {
        urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
      }
      requestBuilder.url(urlBuilder.build()).get();
    } else {
      // 处理 POST, PUT, DELETE 等请求
      MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
      RequestBody body = RequestBody
          .create(Optional.ofNullable(request.getParams().toString()).orElse("{}"), mediaType);
      if (request.getMethod().equalsIgnoreCase("POST")) {
        requestBuilder.url(request.getUrl()).post(body);
      } else if (request.getMethod().equalsIgnoreCase("PUT")) {
        requestBuilder.url(request.getUrl()).put(body);
      } else if (request.getMethod().equalsIgnoreCase("DELETE")) {
        requestBuilder.url(request.getUrl()).delete(body);
      }
    }
    // 添加请求头
    for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
      requestBuilder.header(entry.getKey(), entry.getValue());
    }
    // 发送请求
    try (Response response = client.newCall(requestBuilder.build()).execute()) {
      if (response.isSuccessful() && response.body() != null) {
        return response.body().string();
      } else {
        return "Request failed: " + response.code() + " " + response.message();
      }
    } catch (IOException e) {
      return "Request exception: " + e.getMessage();
    }
  }

  // 递归方法，将嵌套的 JSON 扁平化
  public static Map<String, String> flattenJson(JSONObject json) {
    Map<String, String> map = new HashMap<>();
    for (Map.Entry<String, Object> entry : json.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      if (value instanceof JSONObject) {
        // 递归处理嵌套对象
        map.putAll(flattenJson((JSONObject) value));
      } else {
        // 添加到扁平化 Map
        map.put(key, value.toString());
      }
    }
    return map;
  }


  // 替换 URL 路径中的占位符（${xxx} 格式）
  private static String replacePathVariables(String url, JSONObject params) {
    Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    Matcher matcher = pattern.matcher(url);
    StringBuffer result = new StringBuffer();
    // 匹配并替换占位符
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String value = Optional.ofNullable(params.getString(placeholder)).orElse("");
      matcher.appendReplacement(result, Matcher.quoteReplacement(value));
    }
    matcher.appendTail(result);
    return result.toString();
  }

}
