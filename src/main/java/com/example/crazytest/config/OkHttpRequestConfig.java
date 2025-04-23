package com.example.crazytest.config;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/19 20:56
 * @DESRIPTION 请求封装配置
 */

@Data
@Builder
public class OkHttpRequestConfig {

  private String url;
  private String method;
  private Map<String, String> headers;
  private JSONObject params;
  private int connectTimeout = 10;
  private int readTimeout = 10;
  private int writeTimeout = 10;

}
