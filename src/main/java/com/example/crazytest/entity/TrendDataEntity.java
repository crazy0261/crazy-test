package com.example.crazytest.entity;

import java.time.LocalDate;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/19 00:51
 * @DESRIPTION
 */

@Data
public class TrendDataEntity {

  private LocalDate date;
  private Long apiCaseNum;
  private Long processCaseNum;

}
