package com.example.crazytest.vo;

import com.example.crazytest.entity.TaskSchedule;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 16:19
 * @DESRIPTION
 */

@Data
public class TaskScheduleVO extends TaskSchedule {

  private String ownerName;

  private Boolean isAllCase;

  private List<Long> apiCaseIds;

}
