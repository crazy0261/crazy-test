package com.example.crazytest.aspect;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author
 * @name Menghui
 * @date 2025/3/12 20:46
 * @DESRIPTION 请求日志切面
 */

@Slf4j
@Aspect
@Component
public class GlobalLogAspect {

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && within(com.example.crazytest.controller..*) && execution(public * *(..))")
  public void logAspect() {
  }

  @Around("logAspect()")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

    // 获取请求信息
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();

    assert attributes != null;
    HttpServletRequest request = attributes.getRequest();
    if (ObjectUtils.isEmpty(request)) {
      return joinPoint.proceed();
    }
    String url = request.getRequestURL().toString();
    String ip = request.getRemoteAddr();
    String reqData = this.getParameters(joinPoint, request);
    String header = this.getHeaders(request).toString();

    Object result = null;

    try {
      result = joinPoint.proceed();
      log.info("request：ip:{} , url:{} ,reqParams:{}, header:{},result:{}", ip, url, reqData,
          header, result);
      return result;
    } catch (Throwable e) {
      log.error("ERROR Req：ip:{} , url:{} ,reqParams:{}, header:{},result:{} ", ip, url, reqData,
          header, e.getMessage());
      throw e;
    }
  }

  // 获取请求头
  private Map<String, String> getHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();

      if (Arrays.asList("content-type", "Authorization").contains(headerName)) {
        headers.put(headerName, request.getHeader(headerName));
      }
    }
    return headers;
  }

  // 获取请求参数
  private String getParameters(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
    StringBuilder builder = new StringBuilder("[");

    if (null != joinPoint && null != joinPoint.getSignature()
        && request.getParameterNames() == null) {
      CodeSignature signature = (CodeSignature) joinPoint.getSignature();
      Object[] object = joinPoint.getArgs();

      for (int i = 0; i < object.length; i++) {
        Object str = joinPoint.getArgs()[i];
        String paramsValue = signature.getParameterNames()[i];
        builder.append(paramsValue);
        builder.append(":");
        builder.append(StringUtils.isNotEmpty(str.toString()) ? str : null);
        if (i != object.length - 1) {
          builder.append("-");
        }
      }
    } else if (null != request.getParameterNames()) {
      Map<String, String> parameters = new HashMap<>();
      Enumeration<String> paramsEnumeration = request.getParameterNames();

      while (paramsEnumeration.hasMoreElements()) {
        String paramsName = paramsEnumeration.nextElement();
        String paramsValue = request.getParameter(paramsName);
        parameters.put(paramsName, paramsValue);
      }
      builder.append(parameters);
      System.out.println("------");
    }
    builder.append("]");

    return builder.toString();
  }
}
