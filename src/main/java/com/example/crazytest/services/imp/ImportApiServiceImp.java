package com.example.crazytest.services.imp;

import cn.hutool.json.JSONUtil;
import com.example.crazytest.config.OkHttpRequestConfig;
import com.example.crazytest.convert.ApiManagementConvert;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ImportApiReq;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.services.ImportApiService;
import com.example.crazytest.utils.CUrlUtil;
import com.example.crazytest.utils.RequestUtil;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 22:00
 * @DESRIPTION
 */

@Service
public class ImportApiServiceImp implements ImportApiService {

  @Autowired
  ApiManagementService apiManagementService;

  @Override
  public Boolean importCurlApi(ImportApiReq importApi) {
    Map<String, Object> curlMap = CUrlUtil.parse(importApi.getCurl());

    ApiManagement apiManagement = ApiManagementConvert
        .apiManagementToApiManagementConvert(importApi.getApiName(), importApi.getAppId(),
            curlMap.get("method").toString(), curlMap.get("path").toString(),
            JSONUtil.toJsonStr(curlMap.get("params")));
    return apiManagementService.save(apiManagement);
  }

  @Override
  public void importSwaggerApi(ImportApiReq importApi) throws IOException {

    OkHttpRequestConfig request = OkHttpRequestConfig.builder()
        .url(importApi.getSwaggerUrl())
        .method("GET")
        .build();
    String swaggerJson = Objects.requireNonNull(RequestUtil.sendRequest(request).body()).string();

    OpenAPI openAPI = new OpenAPIV3Parser().readContents(swaggerJson).getOpenAPI();

    // 获取所有接口
    Map<String, PathItem> paths = openAPI.getPaths();
    for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
      String path = entry.getKey();
      PathItem pathItem = entry.getValue();
      // 获取每个接口的 HTTP 方法
      if (pathItem.getGet() != null) {
        operationCovert(importApi.getAppId(), "GET", path, pathItem.getGet());
      }
      if (pathItem.getPost() != null) {
        operationCovert(importApi.getAppId(), "POST", path, pathItem.getPost());
      }
      if (pathItem.getPut() != null) {
        operationCovert(importApi.getAppId(), "PUT", path, pathItem.getPut());
      }
      if (pathItem.getDelete() != null) {
        operationCovert(importApi.getAppId(), "DELETE", path, pathItem.getDelete());
      }
    }
  }

  @Override
  public void operationCovert(Long appId, String method, String path, Operation operation) {

    ApiManagement apiManagement = ApiManagementConvert
        .apiManagementToApiManagementConvert(operation.getSummary(), appId, method, path, null);
    apiManagementService.save(apiManagement);

  }
}
