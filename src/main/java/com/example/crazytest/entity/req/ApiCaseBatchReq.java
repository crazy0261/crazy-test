package com.example.crazytest.entity.req;

import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 12:59
 * @DESRIPTION
 */

@Data
public class ApiCaseBatchReq {

  private List<Long> ids;
  private Long ownerId;
  private String remark;

}
