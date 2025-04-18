package com.example.crazytest.vo;

import com.example.crazytest.entity.TrendDataEntity;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/19 00:46
 * @DESRIPTION
 */

@Data
public class DailyDataCaseVO {
  private List<TrendDataEntity> trendData;
}


