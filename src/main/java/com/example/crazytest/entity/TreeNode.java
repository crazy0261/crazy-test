package com.example.crazytest.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/5 16:30
 * @DESRIPTION
 */

@Data
public class TreeNode {

  String key;
  String title;
  List<TreeNode> children;

  public TreeNode(String key, String title) {
    this.key = key;
    this.title = title;
    this.children = new ArrayList<>();
  }
}
