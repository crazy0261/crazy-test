package com.example.crazytest.utils;

import cn.hutool.core.util.NumberUtil;

/**
 * @author
 * @name Menghui
 * @date 2025/4/18 19:41
 * @DESRIPTION
 */

public class ComputeNumUtil {

  private ComputeNumUtil(){}

  /**
   * 除法
   * @param numerator 分子
   * @param denominator 分母
   * @param scale 小位数
   * @return
   */
  public static double divideNum(long numerator, long denominator, int scale) {
    if (NumberUtil.equals(denominator, 0) || NumberUtil.equals(numerator, 0)) {
      return 0;
    }
    return NumberUtil.div(numerator, denominator, scale);
  }
}
