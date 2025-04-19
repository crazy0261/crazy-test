package com.example.crazytest.job;

import com.example.crazytest.services.TaskScheduleService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 20:05
 * @DESRIPTION
 */

@Slf4j
@Component
public class ExecuteTaskJob {


  @Autowired
  TaskScheduleService taskScheduleService;

  @Autowired
  Executor taskThreadPool;

  @XxlJob("executeTaskJob")
  public ReturnT<String> executeTaskJob() {
    List<Long> taskIds = taskScheduleService.listAllEnable();

    CompletableFuture.allOf(taskIds.stream()
        .map(task -> CompletableFuture
            .runAsync(() -> {
              try {
                taskScheduleService.execute(task);
              } catch (IOException e) {
                log.error("task-id:{},executeTaskJob：{}", task, e.getMessage());
              }
            }, taskThreadPool))
        .toArray(CompletableFuture[]::new)).join();
    return ReturnT.SUCCESS;
  }

}
