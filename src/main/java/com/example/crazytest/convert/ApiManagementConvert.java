package com.example.crazytest.convert;

import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.utils.BaseContext;
import java.util.Objects;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 00:45
 * @DESRIPTION
 */

public class ApiManagementConvert {

  private ApiManagementConvert() {
  }

  public static ApiManagement apiManagementToApiManagementConvert(String name, Long appId,
      String method, String path, String params) {

    ApiManagement apiManagement = new ApiManagement();
    apiManagement.setName(name);
    apiManagement.setApiType("INNER");
    apiManagement.setApplicationId(appId);
    apiManagement.setOwnerId(BaseContext.getUserId());
    apiManagement.setPath(path);
    apiManagement.setMethod(method);
    apiManagement.setRequestParams(Objects.isNull(params) ? "{}" : params);
    apiManagement.setTimeOut(10);

    return apiManagement;
  }

}
