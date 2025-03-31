package com.example.crazytest.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 21:07
 * @DESRIPTION 生成id
 */


public class TimestampRandomIdGenerator {

  public static Long generateId() {
    long timestamp = System.currentTimeMillis();
    int randomNumber = RandomUtil.randomInt(10000, 99999);
    return Long.valueOf(String.valueOf(timestamp).concat(String.valueOf(randomNumber)));
  }

}
