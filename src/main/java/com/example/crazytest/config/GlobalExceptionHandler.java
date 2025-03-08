package com.example.crazytest.config;

import com.example.crazytest.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:34
 * @DESRIPTION 全局一场捕获
 */

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * BusinessException 业务异常捕获
   *
   * @param e
   * @return
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseBody
  public Result<?> handleException(BusinessException e) {
    return Result.fail(e.getCode(), e.getMessage());
  }

  /**
   * Exception 系统异常捕获
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Result<?> handleException(Exception e) {
    return Result.fail(500, e.getMessage());
  }

}
