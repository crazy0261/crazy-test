package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum PriorityEnum {
  PRIORITY_PO_CODE(0, "P0"),
  PRIORITY_P1_CODE(1, "P1"),
  PRIORITY_P2_CODE(2, "P2"),
  PRIORITY_P3_CODE(3, "P3");

  private final int code;
  private final String desc;

  PriorityEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDescByCode(int code) {
    for (PriorityEnum priority : PriorityEnum.values()) {
      if (priority.getCode() == code) {
        return priority.getDesc();
      }
    }
    throw new IllegalArgumentException("Invalid priority code: " + code);
  }
}
