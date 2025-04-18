package com.example.crazytest.entity;

import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/18 22:30
 * @DESRIPTION
 */


@Data
public class CaseResultCountEntity {

  int apiCaseCount;
  int apiCaseSuccessCount;
  int apiCaseFailCount;
  int apiProcessCaseCount;
  int apiCaseProcessSuccessCount;
  int apiCaseProcessFailCount;

}
