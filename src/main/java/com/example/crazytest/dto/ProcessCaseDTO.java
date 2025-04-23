package com.example.crazytest.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 14:29
 * @DESRIPTION
 */

@Data
@Builder
public class ProcessCaseDTO {

  private String treeKey;
  private String name;
  private Long ownerId;
  private Integer status;
  private String recentExecResult;
  private Integer isSubProcess;
  private Integer current;
  private Integer pageSize;

}
