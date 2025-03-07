package com.example.crazytest.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

  // 原始地址 ：http://localhost:8080/swagger-ui.html
  // 美化地址： http://localhost:8088/doc.html

  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
        .group("api")
        .pathsToMatch("/**")
        .build();
  }


}