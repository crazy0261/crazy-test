package com.example.crazytest.factory;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * @author
 * @name Menghui
 * @date 2025/3/19 21:01
 * @DESRIPTION
 */

public class OkHttpClientFactory {

  public static OkHttpClient createClient(int connectTimeout, int readTimeout, int writeTimeout) {
    return new OkHttpClient.Builder()
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .writeTimeout(writeTimeout, TimeUnit.SECONDS)
        .build();
  }
}
