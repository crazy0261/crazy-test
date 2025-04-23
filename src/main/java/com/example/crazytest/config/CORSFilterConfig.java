package com.example.crazytest.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 22:26
 * @DESRIPTION 是指同源 跨域
 */

public class CORSFilterConfig implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // y允许认证信息
    response.addHeader("Access-Control-Allow-Credentials", "true");

    // 允许域名访问
    response.addHeader("Access-Control-Allow-Origin", "*");

    // 支持请求头
    response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

    if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
      servletResponse.getWriter().println("ok");
      return;
    }
    filterChain.doFilter(servletRequest, servletResponse);

  }
}
