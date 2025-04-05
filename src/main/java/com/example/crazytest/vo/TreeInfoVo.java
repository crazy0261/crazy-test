package com.example.crazytest.vo;

import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.entity.TreeNode;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/5 15:41
 * @DESRIPTION
 */

@Data
public class TreeInfoVo extends TreeInfo {


  private transient List<TreeNode> treeList;

}
