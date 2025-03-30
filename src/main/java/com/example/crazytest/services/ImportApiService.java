package com.example.crazytest.services;

import com.example.crazytest.vo.ImportApiVO;
import io.swagger.v3.oas.models.Operation;
import java.io.IOException;

public interface ImportApiService {

   Boolean importCurlApi(ImportApiVO importApi);

   void importSwaggerApi(ImportApiVO importApi) throws IOException;

   void operationCovert(Long appId, String method, String path, Operation operation) ;



   }
