package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.config.OkHttpRequestConfig;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
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
import com.example.crazytest.vo.ApiCaseVO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Service
public class ApiCaseImpl extends ServiceImpl<ApiCaseMapper, ApiCase> implements
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
  UserService userService;

  @Override
  public IPage<ApiCaseVO> list(String name, Long appId, String path, Boolean status,
      String recentExecResult, Long ownerId, Integer current, Integer pageSize) {

    List<Long> pathIds = apiManagementService.getPaths(path);

    IPage<ApiCase> apiCaseIPage = apiCaseRepository
        .list(BaseContext.getTenantId(), name, appId, pathIds, status, recentExecResult, ownerId,
            current, pageSize);

    return apiCaseIPage.convert(apiCase -> {
      ApiCaseVO apiCaseVO = new ApiCaseVO();
      BeanUtils.copyProperties(apiCase, apiCaseVO);
      apiCaseRepository.getById(apiCase.getAppId());

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
    return apiCaseVO;

  }

  @Override
  public boolean save(ApiCase apiCase) {
    apiCase.setTenantId(BaseContext.getTenantId());
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
  public boolean debug(ApiDebugReq apiDebugReq) throws IOException {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());
    AssertUtil.assertTrue(ObjectUtils.isEmpty(apiCase), "用例不存在");

    // 获取域名
    EnvConfig envConfig = envConfigService.getByAppId(apiCase.getAppId());
    DomainInfo domainInfo = domainInfoService.getById(envConfig.getDomainId());

    // 获取API path
    ApiManagement apiManagement = apiManagementService.getById(apiCase.getApiId());

    // 请求头整理
    Map<String, String> headers = new HashMap<>();

    OkHttpRequestConfig request = OkHttpRequestConfig.builder()
        .url(domainInfo.getUrlPath().concat(apiManagement.getPath()))
        .method(apiManagement.getMethod())
        .headers(headers)
        .params(JSON.parseObject(apiDebugReq.getInputParams()))
        .build();
    RequestUtil.sendRequest(request);
    return false;
  }
}
