package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
  // 成功
  SUCCESS(200, "操作成功"),
  BAD_REQUEST(400, "参数错误"),
  UNAUTHORIZED(401, "未授权"),
  NOT_FOUND(404, "资源未找到"),
  INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
  USER_NOT_FOUND(1001, "用户不存在");

  private final int code;
  private final String message;

  ResultEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
