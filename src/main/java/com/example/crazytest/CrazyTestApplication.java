package com.example.crazytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.crazytest.mapper") // 扫描 Mapper 接口所在的包
public class CrazyTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrazyTestApplication.class, args);
  }

}
