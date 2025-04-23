package com.example.crazytest.dto;

import com.example.crazytest.utils.PageParam;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 11:04
 * @DESRIPTION
 */

@Data
public class ApiManagementDTO {

  private Long applicationId;
  private String apiType;
  private String canProdExec;
  private String caseCount;
  private String invokeTimes;
  private Long ownerId;
  private String name;
  private String path;
  private String priority;
  private Integer status;
  private int current = 1;
  private int pageSize = 10;


}
