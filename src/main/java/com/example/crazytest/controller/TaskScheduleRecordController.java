package com.example.crazytest.controller;


import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 定时任务执行记录表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-13
 */
@RestController
@RequestMapping("/task/schedule/record")
public class TaskScheduleRecordController {

  @GetMapping("/list")
  public Result<List<TaskScheduleRecordVO>> list(@RequestParam(value = "scheduleId") Long scheduleId,
      @RequestParam(value = "scheduleBatchId") Long scheduleBatchId,
      @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    return null;
  }

}
