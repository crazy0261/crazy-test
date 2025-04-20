package com.example.crazytest.enums;

public enum NodeStatusEnum {
  INIT("black", "待执行"),
  RUNNING("gray", "执行中"),
  SUCCESS("green", "成功"),
  FAILED("red", "失败"),
  TIMEOUT("orange", "超时");

  private final String color;
  private final String desc;

  NodeStatusEnum(String color, String desc) {
    this.color = color;
    this.desc = desc;
  }

  public String getColor() {
    return color;
  }

  public String getDesc() {
    return desc;
  }

  public static String getValueByType(String type) {
    return NodeStatusEnum.valueOf(type).getColor();
  }
}
