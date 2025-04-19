package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.req.TaskScheduleExecuteReq;
import com.example.crazytest.entity.req.TaskScheduleReq;
import com.example.crazytest.services.TaskScheduleService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.TaskScheduleVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-28
 */
@RestController
@Tag(name = "定时任务")
@RequestMapping("/task/schedule")
public class TaskScheduleController {

  @Autowired
  TaskScheduleService taskScheduleService;

  @GetMapping("/list")
  @Operation(summary = "查询定时任务列表")
  public Result<List<TaskScheduleVO>> list(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "testcaseType", required = false) String testcaseType,
      @RequestParam(value = "ownerName", required = false) String ownerName,
      @RequestParam(value = "enable", required = false) Integer enable,
      @RequestParam(value = "current", defaultValue = "1") Integer current,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    IPage<TaskScheduleVO> taskSchedulePage = taskScheduleService
        .list(name, testcaseType, ownerName, enable, current, pageSize);
    return Result.success(taskSchedulePage.getRecords(), taskSchedulePage.getTotal());
  }

  @PostMapping("/save")
  @Operation(summary = "保存定时任务")
  public Result<Boolean> save(@RequestBody TaskScheduleReq taskSchedule)
      throws JsonProcessingException {
    return Result.success(taskScheduleService.save(taskSchedule));
  }

  @GetMapping("/query")
  @Operation(summary = "详情")
  public Result<TaskScheduleVO> query(@RequestParam(value = "id") Long id)
      throws JsonProcessingException {
    return Result.success(taskScheduleService.queryById(id));
  }

  @PostMapping("/delete")
  @Operation(summary = "删除定时任务")
  public Result<Boolean> delete(@RequestBody TaskSchedule taskSchedule) {
    return Result.success(taskScheduleService.delete(taskSchedule.getId()));
  }

  @PostMapping("/execute")
  @Operation(summary = "执行定时任务")
  public Result<Void> execute(@RequestBody TaskScheduleExecuteReq executeReq) throws IOException {
    taskScheduleService.execute(executeReq.getId());
    return Result.success();
  }

}
