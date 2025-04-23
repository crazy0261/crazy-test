package com.example.crazytest.enums;

import java.util.Arrays;
import java.util.function.BiPredicate;
import lombok.Getter;

@Getter
public enum ConditionTypeEnum {
  EQ("=", String::equals),
  NE("!=", (expected, actual) -> !expected.equals(actual)),
  GT(">", (expected, actual) -> Double.parseDouble(actual) > Double.parseDouble(expected)),
  GE(">=", (expected, actual) -> Double.parseDouble(actual) >= Double.parseDouble(expected)),
  LT("<", (expected, actual) -> Double.parseDouble(actual) < Double.parseDouble(expected)),
  LE("<=", (expected, actual) -> Double.parseDouble(actual) <= Double.parseDouble(expected)),
  CONTAINS("contains", (expected, actual) -> actual.contains(expected)),
  NOT_CONTAINS("not_contains", (expected, actual) -> !actual.contains(expected)),
  IN("IN", (expected, actual) -> Arrays.asList(expected.split(",")).contains(actual)),
  IS_NULL("IS_NULL", (expected, actual) -> actual == null),
  IS_NOT_NULL("IS_NOT_NULL", (expected, actual) -> actual != null);

  private final String condition;
  private final BiPredicate<String, String> predicate;

  ConditionTypeEnum(String condition, BiPredicate<String, String> predicate) {
    this.condition = condition;
    this.predicate = predicate;
  }

  public static ConditionTypeEnum getConditionType(String condition) {
    for (ConditionTypeEnum type : values()) {
      if (type.getCondition().equalsIgnoreCase(condition)) {
        return type;
      }
    }
    throw new IllegalArgumentException("类型不存在: " + condition);
  }

}
