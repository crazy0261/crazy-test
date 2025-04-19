package com.example.crazytest.job;

import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.example.crazytest.services.DailyDataService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/4/19 12:47
 * @DESRIPTION
 */

@Slf4j
@Component
public class CreateDataDailyJob {

  @Autowired
  ProjectManagementRepositoryService projectManagementRepositoryService;

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  DailyDataService dataService;

  @Autowired
  Executor taskThreadPool;

  @XxlJob("createDataDailyJob")
  public ReturnT<String> executeTaskJob() {
    List<ProjectManagement> projectManagementList = projectManagementRepositoryService.listAll();

    CompletableFuture.allOf(projectManagementList.stream()
        .map(projectManagement -> CompletableFuture.runAsync(() -> {
          try {
            dataService.createDataDaily(projectManagement.getId());
          } catch (Exception e) {
            log.error("createDataDailyJob：{}", e.getMessage());
          }
        }, taskThreadPool)).toArray(CompletableFuture[]::new)).join();
    return ReturnT.SUCCESS;
  }
}
