package com.example.crazytest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 21:28
 * @DESRIPTION
 */

public class Constants {

  /**
   * 白名单接口
   */
  public static final List<String> EXCLUDE_PATH = new ArrayList<>(
      Arrays.asList(
          "/user/login",
          "/user/register",
          "/swagger-ui/*",
          "/v3/api-docs/*",
          "/webjars/",
          "/doc.html"
      ));

}
