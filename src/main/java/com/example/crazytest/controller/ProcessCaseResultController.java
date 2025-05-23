package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.services.ProcessCaseResultService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.ProcessCaseResultLogVO;
import com.example.crazytest.vo.ProcessCaseResultVO;
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
 * 场景用例执行结果 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@RestController
@Tag(name = "场景用例执行结果")
@RequestMapping("/process/case/result")
public class ProcessCaseResultController {

  @Autowired
  ProcessCaseResultService processCaseService;

  @GetMapping("/detail/id")
  @Operation(summary = "查询用例执行结果")
  public Result<ProcessCaseResultVO> queryById(@RequestParam(value = "resultId") String resultId) {
    return Result.success(processCaseService.getProcessCaseResult(resultId));
  }

  @GetMapping("/log/list")
  @Operation(summary = "查询用例执行日志")
  public Result<List<ProcessCaseResultLogVO>> logList(
      @RequestParam(value = "caseId", required = true) Long caseId,
      @RequestParam(value = "current", required = true) Integer current,
      @RequestParam(value = "pageSize", required = true) Integer pageSize) {
    IPage<ProcessCaseResultLogVO> page = processCaseService
        .getProcessCaseResultLogs(caseId, current, pageSize);
    return Result.success(page.getRecords(), page.getTotal());

  }

}
