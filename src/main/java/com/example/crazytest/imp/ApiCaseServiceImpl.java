package com.example.crazytest.imp;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.config.OkHttpRequestConfig;
import com.example.crazytest.convert.GetInputParamConvert;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ConditionTypeEnum;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.example.crazytest.repository.TestAccountRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ApiCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.RequestUtil;
import com.example.crazytest.utils.VariablesUtil;
import com.example.crazytest.vo.ApiCaseVO;
import com.example.crazytest.vo.AssertReqVo;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.ParamsListVO;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 测试用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Slf4j
@Service
public class ApiCaseServiceImpl extends ServiceImpl<ApiCaseMapper, ApiCase> implements
    ApiCaseService {

  @Autowired
  ApiCaseRepositoryService apiCaseRepository;

  @Autowired
  ApiManagementService apiManagementService;

  @Autowired
  ApplicationManagementService applicationManagementService;

  @Autowired
  EnvConfigService envConfigService;

  @Autowired
  DomainInfoService domainInfoService;

  @Autowired
  TestAccountRepositoryService testAccountService;

  @Autowired
  UserService userService;

  @Autowired
  VariablesUtil variablesUtil;

  @Autowired
  GetInputParamConvert getInputParamConvert;

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @Autowired
  ApiCaseResultRepositoryService apiCaseResultRepositoryService;


  @Override
  public IPage<ApiCaseVO> list(String name, Long appId, String path, Boolean status,
      String recentExecResult, Long ownerId, Integer current, Integer pageSize) {

    List<Long> pathIds = apiManagementService.getPaths(path);

    List<Long> allIds = StringUtils.isNotEmpty(recentExecResult) ? apiCaseResultService
        .listResult(BaseContext.getTenantId(), recentExecResult) : null;

    IPage<ApiCase> apiCaseIPage = apiCaseRepository
        .list(BaseContext.getTenantId(), name, appId, pathIds, status, allIds, ownerId,
            current, pageSize);

    return apiCaseIPage.convert(apiCase -> {
      ApiCaseVO apiCaseVO = new ApiCaseVO();
      BeanUtils.copyProperties(apiCase, apiCaseVO);
      apiCaseRepository.getById(apiCase.getAppId());

      ApiCaseResult apiCaseResult = apiCaseResultRepositoryService
          .lastApiCaseResult(apiCase.getId());
      apiCaseVO.setRecentExecResult(
          ObjectUtils.isEmpty(apiCaseResult) ? null : apiCaseResult.getStatus());
      apiCaseVO.setRecentExecTime(
          ObjectUtils.isEmpty(apiCaseResult) ? null : apiCaseResult.getUpdateTime());

      User user = userService.getById(apiCase.getOwnerId());
      apiCaseVO.setAppName(applicationManagementService.getById(apiCase.getAppId()).getName());
      apiCaseVO.setPath(apiManagementService.getById(apiCase.getApiId()).getPath());
      apiCaseVO.setOwnerName(Optional.ofNullable(user).map(User::getName).orElse(""));
      return apiCaseVO;
    });
  }

  @Override
  public ApiCaseVO getById(Long id) {
    ApiCase apiCase = apiCaseRepository.getById(id);
    ApiCaseVO apiCaseVO = new ApiCaseVO();
    BeanUtils.copyProperties(apiCase, apiCaseVO);

    EnvConfig envConfig = envConfigService.getByAppId(apiCase.getAppId());
    apiCaseVO.setDomainUrl(domainInfoService.getById(envConfig.getDomainId()).getUrlPath());
    apiCaseVO.setAssertsArray(Objects.isNull(apiCase.getAsserts()) ? new ArrayList<>()
        : JSON.parseArray(apiCase.getAsserts(), AssertReqVo.class));
    return apiCaseVO;

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean save(ApiCaseReq apiCaseReq) {
    apiCaseReq.setTenantId(BaseContext.getTenantId());
    ApiCase apiCase = new ApiCase();
    BeanUtils.copyProperties(apiCaseReq, apiCase);
    apiCase
        .setOwnerId(Optional.ofNullable(apiCaseReq.getOwnerId()).orElse(BaseContext.getUserId()));
    apiCase.setAsserts(
        Optional.ofNullable(apiCaseReq.getAssertsArray()).map(JSON::toJSONString).orElse(""));

    return apiCaseRepository.saveOrUpdate(apiCase);
  }

  @Override
  public List<Map<String, Object>> allList() {
    List<ApiCase> apiCaseList = apiCaseRepository.allList(BaseContext.getTenantId());

    return apiCaseList.stream().map(apiCase -> {
      Map<String, Object> map = new HashMap<>();
      map.put("id", apiCase.getId());
      map.put("name", apiCase.getName());
      return map;
    }).collect(Collectors.toList());
  }

  @Override
  public ResultApiVO debug(ApiDebugReq apiDebugReq) throws IOException {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());
    AssertUtil.assertTrue(ObjectUtils.isEmpty(apiCase), "用例不存在");

    // 获取域名
    EnvConfig envConfig = envConfigService.getByAppId(apiCase.getAppId());
    DomainInfo domainInfo = domainInfoService.getById(envConfig.getDomainId());

    // 环境变量
    Map<String, String> envionmentVariables = new HashMap<>();

    // 获取API path
    ApiManagement apiManagement = apiManagementService.getById(apiCase.getApiId());

    // 前置参数
    JSONObject preParams = JSON.parseObject(apiDebugReq.getInputParams());
    JSONArray paramsArr = preParams.getJSONArray("envVariables");
    List<ParamsListVO> paramsArrList = Optional.ofNullable(paramsArr)
        .map(item -> item.toJavaList(ParamsListVO.class)).orElse(new ArrayList<>());

    // 获取断言
    List<AssertReqVo> assertsArray = Optional.ofNullable(apiCase.getAsserts())
        .map(str -> JSON.parseArray(str, AssertReqVo.class)).orElse(Collections.emptyList());

    // 获取token
    String preParamsKey = preParams.keySet().stream().findFirst().orElse("");
    // 判断是测试账号通过请求
    Object preParamsValue = preParams.get(preParamsKey);
    boolean checkJson = preParamsValue instanceof JSONObject;
    if (!checkJson) {
      envionmentVariables.putAll(
          getInputParamConvert.jsonMapConvert(JSON.parseObject(apiDebugReq.getInputParams())));
    }
    String accountId =
        checkJson ? preParams.getJSONObject(preParamsKey).getString("testaccountID") : "";

    if (StringUtils.isNotEmpty(accountId)) {
      TestAccount user = testAccountService.getById(accountId);
      AssertUtil.assertNotNull(user, "测试账号不存在,请检查！");
      AssertUtil.assertTrue(ObjectUtils.isEmpty(user.getToken()), "token为空或已过期，请检查！");
      envionmentVariables.put("token", user.getToken());
    }

    // 请求头整理
    Map<String, String> headers = variablesUtil
        .formatHeader(preParamsKey, envionmentVariables, paramsArrList, envConfig, apiCase);

    // 请求参数格式化
    JSONObject paramsJson = variablesUtil
        .formatParams(apiCase.getRequestParams(), envionmentVariables);

    OkHttpRequestConfig request = OkHttpRequestConfig.builder()
        .url(domainInfo.getUrlPath().concat(apiManagement.getPath()))
        .method(apiManagement.getMethod())
        .headers(headers)
        .params(paramsJson)
        .build();

    long startTime = System.currentTimeMillis();
    Response response = RequestUtil.sendRequest(request);
    JSONObject body = JSON.parseObject(Objects.requireNonNull(response.body()).string());
    long endTime = System.currentTimeMillis();

    return ResultApiVO
        .builder()
        .requestParams(paramsJson)
        .requestUrl(request.getUrl())
        .requestHeaders(request.getHeaders())
        .response(body)
        .assertResultVo(assertResult(assertsArray, body))
        .startExecTime(DateUtil.formatDateTime(DateUtil.date(startTime)))
        .execTime(endTime - startTime)
        .build();
  }

  @Override
  public AssertResultVo assertResult(List<AssertReqVo> assertReqVos, JSONObject body) {
    AssertResultVo assertResult = new AssertResultVo();
    assertResult.setPass(Boolean.TRUE);

    Integer code = body.getInteger("code");
    if (code != 200) {
      assertResult.setPass(Boolean.FALSE);
      return assertResult;
    }

    if (assertReqVos.isEmpty()) {
      return assertResult;
    }

    boolean allAssertionsPass = assertReqVos.stream()
        .allMatch(assertReqVo -> checkAssertion(assertReqVo, body));

    assertResult.setPass(allAssertionsPass);
    return assertResult;
  }

  @Override
  public Boolean checkAssertion(AssertReqVo assertReqVo, JSONObject body) {
    String jsonPath = assertReqVo.getJsonPath();
    String expectedValue = assertReqVo.getExpectValue();
    String actualValue;

    try {
      if (jsonPath.contains("size()")) {
        String path = jsonPath.split("size\\(\\)")[0];
        JSONArray jsonArray = JSON.parseArray(JSONPath.eval(body, path).toString());
        actualValue = String.valueOf(jsonArray.size());
      } else {
        actualValue = JSONPath.eval(body, jsonPath).toString();
      }
    } catch (Exception e) {
      return Boolean.FALSE;
    }

    return assertConditionResult(assertReqVo.getCondition(), expectedValue, actualValue);
  }

  @Override
  public Boolean assertConditionResult(String condition, String expectValue, String actualValue) {

    ConditionTypeEnum conditionType = ConditionTypeEnum.getConditionType(condition);
    return conditionType.getPredicate().test(expectValue, actualValue);
  }

  @Override
  public Boolean copyApiCase(ApiDebugReq apiDebugReq) {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());
    apiCase.setId(null);
    apiCase.setName(apiCase.getName().concat("-copy"));
    return apiCaseRepository.save(apiCase);
  }

  @Override
  public Boolean delete(ApiDebugReq apiDebugReq) {
    return apiCaseRepository.updateApiCase(apiDebugReq);
  }
}
