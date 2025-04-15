package com.example.crazytest.enums;

public enum NodeTypeEnum {
  START_NODE("StartNode"),
  END_NODE("EndNode"),
  TEST_CASE_NODE("TestCaseNode"),
  PRE_STEP_NODE("PreStepNode"),
  SUB_PROCESS_NODE("SubProcessNode"),
  CONDITION_NODE("ConditionNode");


  private final String typeName;

  NodeTypeEnum(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static NodeTypeEnum fromString(String type) {
    for (NodeTypeEnum nodeType : values()) {
      if (nodeType.typeName.equalsIgnoreCase(type)) {
        return nodeType;
      }
    }
    throw new IllegalArgumentException("没有节点类型: " + type);
  }
}