package com.example.crazytest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfig {

  // 原始地址 ：http://localhost:8080/swagger-ui/index.html
  // 美化地址： http://localhost:8080/doc.html#/home
  //json 地址： http://localhost:8080/v3/api-docs/crazy-test

  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
        .group("crazy-test")
        .pathsToMatch("/**")
        .build();
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Crazy Test Api") // API 标题
            .description("Crazy Test 测试平台接口文档") // API 描述
            .version("1.0.0") // API 版本
            .contact(new Contact()
                .name("Menghui") // 作者名称
                .url("https://example.com") // 作者链接
                .email("dingmenghui02@163.com.com") // 作者邮箱
            )
        )
        .addServersItem(new Server()
            .url("http://localhost:8080") // 本地开发环境 URL
            .description("Local Development Server")
        );
  }


}