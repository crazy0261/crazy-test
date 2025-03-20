package com.example.crazytest.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 21:31
 * @DESRIPTION
 */

@Configuration
@EnableAsync
public class ThreadPoolConfig {

  @Bean
  public Executor normalThreadPool() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //设置核心线程数
    executor.setCorePoolSize(50);
    //设置核心线程数
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(Integer.MAX_VALUE);
    // 设置线程活跃时间（秒）
    executor.setKeepAliveSeconds(60);
    //线程名称前缀
    executor.setThreadNamePrefix("normalThreadPool--");
    //设置拒绝策略
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //增加线程池修饰类
//    executor.setTaskDecorator(new CustomTaskDecorator());
    //执行初始化
    executor.initialize();
    return executor;
  }

}
