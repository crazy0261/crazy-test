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
  private Result() {}

  // 成功的响应（带数据）
  public static <T> Result<T> success(T data) {
    Result<T> response = new Result<>();
    response.code = 200; // 成功的状态码
    response.message = "操作成功"; // 默认的成功提示
    response.data = data;
    return response;
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
