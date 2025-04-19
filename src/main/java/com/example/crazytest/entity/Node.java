package com.example.crazytest.entity;

import com.example.crazytest.enums.NodeStatusEnum;
import com.example.crazytest.enums.NodeTypeEnum;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:08
 * @DESRIPTION 节点信息
 */

@Data
public class Node {
  private String id;
  private NodeTypeEnum type;
  private NodeData data;
  private Boolean dragging;
  private Boolean selected;
  private Double width;
  private Double height;
  private Position position;
  private Position positionAbsolute;


  @Data
  public static class Position {
    private Double x;
    private Double y;
  }

  @Data
  public static class NodeData {
    private String borderColor;
    private String label;
    private String color;
  }

  public void updateStatus(NodeStatusEnum status) {
    this.data.setColor(status.getColor());
  }
}