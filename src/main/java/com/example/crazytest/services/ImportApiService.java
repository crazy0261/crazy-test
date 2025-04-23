package com.example.crazytest.services;

import com.example.crazytest.entity.req.ImportApiReq;
import io.swagger.v3.oas.models.Operation;
import java.io.IOException;

public interface ImportApiService {

  Boolean importCurlApi(ImportApiReq importApi);

  void importSwaggerApi(ImportApiReq importApi) throws IOException;

  void operationCovert(Long appId, String method, String path, Operation operation);

}
