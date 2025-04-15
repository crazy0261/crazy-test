package com.example.crazytest.entity;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:08
 * @DESRIPTION 边信息
 */

@Data
public class Edge {

  private String id;
  private String source;
  private String target;
  private String sourceHandle;
  private String targetHandle;
  private String label;
}