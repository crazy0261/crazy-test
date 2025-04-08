package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
  // 成功
  SUCCESS(200, "操作成功"),
  BAD_REQUEST(400, "参数错误"),
  TOKEN_NOT(401, "token不存在"),
  UNAUTHORIZED(401, "token过期"),
  NOT_FOUND(404, "资源未找到"),
  INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
  USER_NOT_FOUND(1001, "用户不存在"),
  USER_STOP_STATUS(400, "该用户已被停用"),
  USER_PASSWORD_FAIL(400, "密码错误"),
  JSON_PATH_FORMAT_FAIL(400, "JSON Path格式错误，请检查！"),
  USER_PROJECT_NOT_FAIL(400, "项目不存在"),
  TREE_NODE_NOT_KEY_FAIL(400, "请先点击节点"),
  APP_ID_EXIST_FAIL(400, "该应用下已存在该域名的环境配置"),
  DATA_SOURCE_NOT_EXIST(400, "数据源不存在"),
  NAME_REPEAT(400, "名称已存在"),
  USER_EXIST_FAIL(400, "用户已存在");

  private final int code;
  private final String message;

  ResultEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
