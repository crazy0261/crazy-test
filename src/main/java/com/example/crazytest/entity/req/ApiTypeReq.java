package com.example.crazytest.entity.req;

import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 20:42
 * @DESRIPTION 批量处理参数
 */

@Data
public class ApiTypeReq {

  private List<Long> apiIds;
  private String apiType;
  private String remark;
  private Long appId;
  private Integer priority;
  private Integer canProdExec;
  private Long ownerId;

}
