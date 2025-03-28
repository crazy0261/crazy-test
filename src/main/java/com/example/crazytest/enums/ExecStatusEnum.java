package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum ExecStatusEnum {
  INIT("INIT", "white", "初始化", false),
  RUNNING("RUNNING", "gray", "执行中", false),
  SUCCESS("SUCCESS", "green", "执行成功", true),
  FAIL("FAIL", "red", "执行失败", true),
  TIMEOUT("TIMEOUT", "red", "执行超时", true),
  IGNORE("IGNORE", "white", "执行忽略", true),
  ;

  private final String value;
  private final String nodeColor;   // 节点颜色
  private final String desc;
  private Boolean isFinalState;   // 是否是终态

  ExecStatusEnum(String value, String nodeColor, String desc, Boolean isFinalState) {
    this.value = value;
    this.nodeColor = nodeColor;
    this.desc = desc;
  }
}
