package com.example.crazytest.dto;

import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ProcessCase;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/21 17:22
 * @DESRIPTION
 */

@Data
public class TaskDailyDTO {

  List<ApiCase> notTaskApiCases;
  List<ProcessCase> notTaskProcessCases;

}
