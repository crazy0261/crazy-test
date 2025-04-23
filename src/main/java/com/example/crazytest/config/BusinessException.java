package com.example.crazytest.config;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:29
 * @DESRIPTION 自定义异常类
 */

public class BusinessException extends RuntimeException {

  private final int code;
  private final String message;

  public BusinessException(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
