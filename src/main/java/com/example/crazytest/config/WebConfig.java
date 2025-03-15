package com.example.crazytest.config;

import com.example.crazytest.utils.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 22:39
 * @DESRIPTION
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new JWTInterceptor())
//        .addPathPatterns("/crazy/**")
        .excludePathPatterns(Constants.EXCLUDE_PATH);
  }

}
