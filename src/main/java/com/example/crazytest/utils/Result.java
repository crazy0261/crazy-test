package com.example.crazytest.utils;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/7 23:07
 * @DESRIPTION 返回结果
 */

@Data
public class Result<T> {

  private int code;
  private boolean success = true;
  private String message;
  private long total;
  private T data;

  // 构造方法（隐藏默认构造方法）
  private Result() {
  }

  // 成功的响应（带数据）
  public static <T> Result<T> success(int code, T data, String message, long total,
      boolean success) {
    Result<T> response = new Result<>();
    response.code = code; // 成功的状态码
    response.message = message; // 默认的成功提示
    response.data = data;
    response.total = total;
    response.success = success;
    return response;
  }

  public static <T> Result<T> success(int code, T data, String message, long total) {
    return success(code, data, message, total, true);
  }

  public static <T> Result<T> success(int code, T data, long total) {
    return success(code, data, "操作成功", total);
  }

  // 成功的响应（带数据）
  public static <T> Result<T> success(T data, long total) {
    return success(200, data, total);
  }

  // 成功的响应（带数据）
  public static <T> Result<T> success(T data) {
    return success(200, data, 0);
  }

  // 成功的响应（无数据）
  public static <T> Result<T> success() {
    return success(null);
  }

  // 失败的响应（带错误信息）
  public static <T> Result<T> fail(int code, String message) {
    Result<T> response = new Result<>();
    response.code = code; // 自定义错误状态码
    response.message = message; // 错误提示
    response.data = null;
    return response;
  }

}
