package com.example.crazytest.entity;

import java.time.LocalDate;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/19 00:41
 * @DESRIPTION
 */

@Data
public class DateTimeReq {

  LocalDate startTime;
  LocalDate endTime;

}
