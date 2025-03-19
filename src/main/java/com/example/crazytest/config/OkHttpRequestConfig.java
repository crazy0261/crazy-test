package com.example.crazytest.config;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/19 20:56
 * @DESRIPTION 请求封装配置
 */

@Data
public class OkHttpRequestConfig {

  private String url;
  private String method;
  private Map<String, String> headers;
  private JSONObject params;
  private int connectTimeout;
  private int readTimeout;
  private int writeTimeout;

  // 私有构造函数
  private OkHttpRequestConfig() {
    this.headers = new HashMap<>();
    this.params = new JSONObject();
    this.connectTimeout = 10;
    this.readTimeout = 10;
    this.writeTimeout = 10;
  }
}
