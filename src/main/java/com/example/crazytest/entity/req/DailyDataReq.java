package com.example.crazytest.entity.req;

import java.time.LocalDate;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/17 22:12
 * @DESRIPTION
 */

@Data
public class DailyDataReq {

  LocalDate startDate;
  LocalDate endDate;

}
