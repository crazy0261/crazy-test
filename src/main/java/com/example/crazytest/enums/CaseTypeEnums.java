package com.example.crazytest.enums;

import lombok.Getter;

@Getter
public enum CaseTypeEnums {

  API_CASE_TYPE("API_CASE", "接口用例"),
  PROCESS_CASE_TYPE("PROCESS_CASE", "场景用例");

  private final String type;
  private final String desc;

  CaseTypeEnums(String type, String desc) {
    this.type = type;
    this.desc = desc;
  }
}
