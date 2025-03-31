package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum ExecModeEnum {

  AUTO("auto", "自动执行"),
  MANUAL("manual", "手动执行");

  private final String value;
  private final String desc;

  ExecModeEnum(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }
}
