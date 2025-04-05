package com.example.crazytest.utils;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 21:33
 * @DESRIPTION 上下文信息
 */

public class BaseContext {

  private static ThreadLocal<Long> userId = new ThreadLocal<>();
  private static ThreadLocal<String> userAccount = new ThreadLocal<>();
  private static ThreadLocal<String> userName = new ThreadLocal<>();
  private static ThreadLocal<String> traceId = new ThreadLocal<>();
  private static ThreadLocal<Long> selectProjectId = new ThreadLocal<>();


  public static void setUserId(Long user) {
    userId.set(user);
  }

  public static Long getUserId() {
    return userId.get();
  }


  public static void setUserAccount(String account) {
    userAccount.set(account);
  }

  public static String getUserAccount() {
    return userAccount.get();
  }

  public static void setUserName(String userByName) {
    userName.set(userByName);
  }

  public static String getUserName() {
    return userName.get();
  }

  public static void setTraceId(String trace) {
    traceId.set(trace);
  }

  public static String getTraceId() {
    return traceId.get();
  }

  public static void setSelectProjectId(Long selectProject) {
    selectProjectId.set(selectProject);
  }

  public static Long getSelectProjectId() {
    return selectProjectId.get();
  }

  public static void clear() {
    userId.remove();
    userAccount.remove();
    userName.remove();
    traceId.remove();
    selectProjectId.remove();
  }

}
