package com.example.crazytest.vo;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/17 22:09
 * @DESRIPTION
 */

@Data
public class CoreIndicatorsListVO {

  private Long userCount;
  private Long appCount;
  private Long apiCount;
  private Long bugCount;
  private Long caseCount;
  private Long apiCaseCount;
  private Long processCaseCount;
  private Double caseSuccessRate;
  private Long coverageIsApiCount;
  private Long coverageNotApiCount;
  private Double coverageApiRate;

}
