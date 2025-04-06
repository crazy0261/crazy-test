package com.example.crazytest.entity.req;

import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 17:25
 * @DESRIPTION
 */

@Data
public class ProcessCaseBatchReq {

  List<Long> caseIds;
  private Long ownerId;
  private String treeKey;
  private String remark;

}
