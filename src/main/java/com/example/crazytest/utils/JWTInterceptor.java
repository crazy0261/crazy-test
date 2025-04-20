package com.example.crazytest.utils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.crazytest.config.Constants;
import com.example.crazytest.entity.User;
import com.example.crazytest.enums.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 21:22
 * @DESRIPTION JWT 拦截器
 */

public class JWTInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws IOException {
    MDC.put("traceId", UUID.randomUUID().toString().replace("-", ""));

    Result result;

    // 放行
    if (Constants.EXCLUDE_PATH.contains(request.getRequestURI())) {
      BaseContext.setSelectProjectId(null);
      BaseContext.setUserAccount(null);
      BaseContext.setUserName(null);
      BaseContext.setUserId(null);

      return Boolean.TRUE;
    }

    String token = request.getHeader("Authorization");
    if (StringUtils.isNotEmpty(token)) {
      try {
        JWTUtil.getDecodeToken(token);
        User user = JWTUtil.getUserByToken(token);

        assert user != null;
        BaseContext.setSelectProjectId(user.getProjectId());
        BaseContext.setUserAccount(user.getAccount());
        BaseContext.setUserName(user.getName());
        BaseContext.setUserId(user.getId());
        return Boolean.TRUE;
      } catch (TokenExpiredException e) {
        result = Result
            .fail(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
      }
    } else {
      result = Result.fail(ResultEnum.TOKEN_NOT.getCode(), ResultEnum.TOKEN_NOT.getMessage());
    }

    String json = new ObjectMapper().writeValueAsString(result);
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().println(json);
    return Boolean.FALSE;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {
    MDC.clear();
  }

}
