package com.example.crazytest.utils;

import org.springframework.scheduling.support.CronExpression;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 01:08
 * @DESRIPTION
 */

public class CronUtil {

  // 私有构造函数，防止实例化
  private CronUtil() {
  }


  /**
   * 验证 Cron 表达式是否符合高级 Cron 表达式的规则（6 字段格式）
   *
   * @param cronExpression 需要验证的 Cron 表达式
   * @throws IllegalArgumentException 如果表达式不符合规则，则抛出异常
   */
  public static void cronCheckRule(String cronExpression) throws IllegalArgumentException {
    // 尝试解析 Cron 表达式
    try {
      CronExpression.parse(cronExpression);
    } catch (Exception e) {
      AssertUtil.assertTrue(Boolean.TRUE, "Cron不符合规则,请检查!");
    }
  }
}