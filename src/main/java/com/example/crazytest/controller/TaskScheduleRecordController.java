package com.example.crazytest.controller;


import com.example.crazytest.services.TaskScheduleRecordService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.CaseResultDetailVO;
import com.example.crazytest.vo.TaskBatchConvergeVO;
import com.example.crazytest.vo.TaskScheduleRecordVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

@Tag(name = "定时任务执行记录表")
@RestController
@RequestMapping("/task/schedule/record")
public class TaskScheduleRecordController {

  @Autowired
  TaskScheduleRecordService taskScheduleRecordService;

  @GetMapping("/list")
  @Operation(summary = "查询定时任务执行记录")
  public Result<List<TaskScheduleRecordVO>> list(
      @RequestParam(value = "scheduleId") Long scheduleId,
      @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    return Result.coverPage(taskScheduleRecordService.listPage(scheduleId, current, pageSize));
  }

  @GetMapping("/case/detail/list")
  @Operation(summary = "批次任务用例详情列表")
  public Result<CaseResultDetailVO> scheduleBatchList(
      @RequestParam(value = "scheduleId") Long scheduleId,
      @RequestParam(value = "scheduleBatchId") String scheduleBatchId,
      @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    return Result.success(taskScheduleRecordService
        .scheduleBatchList(scheduleId, scheduleBatchId, current, pageSize));
  }

  @GetMapping("/batch/converge")
  @Operation(summary = "批次任务用例详情统计")
  public Result<TaskBatchConvergeVO> taskBatchConverge(
      @RequestParam(value = "scheduleId") Long scheduleId,
      @RequestParam(value = "scheduleBatchId") String scheduleBatchId)
      throws JsonProcessingException {
    return Result.success(taskScheduleRecordService.taskBatchConverge(scheduleId, scheduleBatchId));

  }

}
