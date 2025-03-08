package com.example.crazytest.utils;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 21:33
 * @DESRIPTION 上下文信息
 */

public class BaseContext {

  private static ThreadLocal<String> tenantId = new ThreadLocal<>();
  private static ThreadLocal<Long> userId = new ThreadLocal<>();
  private static ThreadLocal<String> userAccount = new ThreadLocal<>();
  private static ThreadLocal<String> userName = new ThreadLocal<>();
  private static ThreadLocal<String> traceId = new ThreadLocal<>();
  private static ThreadLocal<Long> selectProjectId = new ThreadLocal<>();


  // todo 修改成读取数据库中租户信息  根据id  现在是处理默认值    目前这个地方不需要默认值
  public static void setTenantId(String tenant) {
    tenantId.set(tenant);
  }

  public static String getTenantId() {
    return tenantId.get();
  }

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
    tenantId.remove();
    userId.remove();
    userAccount.remove();
    userName.remove();
    traceId.remove();
    selectProjectId.remove();
  }

}
