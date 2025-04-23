package com.example.crazytest.utils;

import com.example.crazytest.config.BusinessException;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:24
 * @DESRIPTION 断言
 */

public class AssertUtil {

  private AssertUtil() {
  }

  /**
   * 断言对象不为空
   *
   * @param object
   * @param message
   */
  public static void assertNotNull(Object object, String message) {
    if (object == null) {
      throw new BusinessException(400, message);
    }
  }


  public static void assertNotTrue(Boolean bool, String message) {
    if (Boolean.FALSE.equals(bool)) {
      throw new BusinessException(400, message);
    }
  }

  public static void assertTrue(Boolean bool, String message) {
    if (Boolean.TRUE.equals(bool)) {
      assertTrue(true, 400, message);
    }
  }

  /**
   * 断言对象为空
   *
   * @param bool
   * @param code
   * @param message
   */
  public static void assertTrue(Boolean bool, int code, String message) {
    if (Boolean.TRUE.equals(bool)) {
      throw new BusinessException(code, message);
    }
  }

}
