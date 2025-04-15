package com.example.crazytest.services.imp;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.config.OkHttpRequestConfig;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.services.CaseDebugService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.services.EncryptInfoService;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.utils.RequestUtil;
import com.example.crazytest.utils.VariablesUtil;
import com.example.crazytest.vo.AssertVO;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/15 21:40
 * @DESRIPTION
 */

@Service
public class CaseDebugServiceImp implements CaseDebugService {

  @Autowired
  EnvConfigService envConfigService;

  @Autowired
  DomainInfoService domainInfoService;

  @Autowired
  ApiManagementService apiManagementService;


  @Autowired
  EncryptInfoService encryptInfoService;

  @Autowired
  VariablesUtil variablesUtil;

  @Autowired
  ApiCaseService apiCaseService;

  @Override
  public ResultApiVO processApiCaseDebug(ProcessCaseNode processCaseNode,
      ExecutionProcessContext context) throws IOException {

    ApiDebugReq apiDebugReq = context.getApiDebugReq();
    // api信息-url
    ApiManagement apiManagement = apiManagementService.getById(processCaseNode.getApiId());

    // 环境信息域名
    EnvConfig envConfig = envConfigService
        .getByAppId(processCaseNode.getAppId(), apiDebugReq.getEnvId());
    DomainInfo domainInfo = domainInfoService.getById(envConfig.getDomainId());

    // 全局参数
    Map<String, String> envVariables = RequestUtil.envVariablesPutAll(apiDebugReq.getInputParams());
    Map<String, String> envParameter = context.getEnvParameter();
    envParameter.putAll(envVariables);

    // 请求头整理
    Map<String, String> headers = variablesUtil
        .formatHeader(envParameter, envConfig, processCaseNode.getRequestHeaders());

    // 请求参数格式化
    JSONObject paramsJson = variablesUtil
        .formatParams(processCaseNode.getRequestParams(), envParameter);

    // 获取断言
    List<AssertVO> assertsArray = Optional.ofNullable(processCaseNode.getAssertList())
        .map(str -> JSON.parseArray(str, AssertVO.class)).orElse(Collections.emptyList());

    // 获取加密信息
    JSONObject encryptJson = new JSONObject();
    JSONObject encryptJsonParams = new JSONObject();
    if (Objects.nonNull(processCaseNode.getSecretId())) {
      encryptJson = encryptInfoService
          .getEncryptInfoEnv(processCaseNode.getSecretId(), apiDebugReq.getEnvId());
      headers.put("Authorization", encryptJson.getString("key"));

      if (Objects.nonNull(paramsJson)) {
        encryptJsonParams = apiCaseService.encryptParam(encryptJson.getString("secret"), paramsJson);
      }
    }

    OkHttpRequestConfig request = RequestUtil.requestConfig(
        domainInfo.getUrlPath().concat(apiManagement.getPath()),
        apiManagement.getMethod(), headers, paramsJson);

    long startTime = System.currentTimeMillis();
    Response response = RequestUtil.sendRequest(request);
    JSONObject body = JSON.parseObject(Objects.requireNonNull(response.body()).string());
    long endTime = System.currentTimeMillis();

    return ResultApiVO
        .builder()
        .requestParams(Optional.ofNullable(encryptJsonParams).orElse(paramsJson))
        .requestUrl(request.getUrl())
        .requestHeaders(request.getHeaders())
        .response(encryptJson.isEmpty() ? apiCaseService
            .decryptRequestBody(encryptJson.getString("secret"), body)
            : body)
        .assertResultVo(
            CollUtil.isNotEmpty(assertsArray) ? apiCaseService.assertResult(assertsArray, body)
                : null)
        .startExecTime(DateUtil.formatDateTime(DateUtil.date(startTime)))
        .execTime(endTime - startTime)
        .build();
  }
}
