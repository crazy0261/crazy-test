package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.entity.req.EnvConfigReq;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.vo.EnvConfigVO;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ParamsListVO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 20:47
 * @DESRIPTION
 */

@Service
public class EnvConfigServiceImpl implements EnvConfigService {

  @Autowired
  EnvConfigRepositoryService envConfigRepositoryService;

  @Autowired
  ApplicationManagementService applicationManagementService;

  @Autowired
  DomainInfoService domainInfoService;

  @Override
  public IPage<EnvConfigVO> list(String appid, String name, String domainName, int current,
      int pageSize) {

    List<String> domainIds = domainInfoService.getByNameList(domainName).stream()
        .map(domainInfo -> String.valueOf(domainInfo.getId())).collect(Collectors.toList());

    IPage<EnvConfig> envConfigIPage = envConfigRepositoryService
        .list(BaseContext.getTenantId(), appid, name, domainIds, current, pageSize);

    return envConfigIPage.convert(envConfig -> {
      EnvConfigVO envConfigVo = new EnvConfigVO();
      DomainInfo domainInfo = domainInfoService.getById(envConfig.getDomainId());
      BeanUtils.copyProperties(envConfig, envConfigVo);
      envConfigVo.setAppName(applicationManagementService.getById(envConfig.getAppId()).getName());
      envConfigVo.setDomainUrl(domainInfo.getUrlPath());
      envConfigVo.setDomainName(domainInfo.getName());
      return envConfigVo;
    });
  }

  @Override
  public EnvConfig getByAppId(Long appId) {
    return envConfigRepositoryService.getByAppId(appId);
  }

  @Override
  public boolean save(EnvConfigReq envConfigReq) {
    EnvConfig envConfig = new EnvConfig();

    List<EnvConfig> envConfigs = envConfigRepositoryService
        .getEnvConfigByAppIdAndDomainId(envConfigReq.getAppId(), envConfigReq.getDomainId());
    AssertUtil.assertTrue(ObjectUtils.isEmpty(envConfigs), "该应用下已存在该域名的环境配置");

    BeanUtils.copyProperties(envConfigReq, envConfig);
    envConfig.setTenantId(BaseContext.getTenantId());
    envConfig.setRequestHeaders(JSON.toJSONString(envConfigReq.getRequestHeaders()));
    envConfig.setEnvVariables(JSON.toJSONString(envConfigReq.getEnvVariables()));

    return envConfigRepositoryService.saveOrUpdate(envConfig);
  }

  @Override
  public EnvConfigReq queryById(Long id) {
    EnvConfig envConfig = envConfigRepositoryService.getById(id);
    EnvConfigReq envConfigReq = new EnvConfigReq();
    BeanUtils.copyProperties(envConfig, envConfigReq);
    envConfigReq
        .setRequestHeaders(JSONArray.parseArray(envConfig.getRequestHeaders(), ParamsListVO.class));
    envConfigReq
        .setEnvVariables(JSONArray.parseArray(envConfig.getEnvVariables(), ParamsListVO.class));
    return envConfigReq;
  }

  @Override
  public List<EnvConfig> listAll() {
    return envConfigRepositoryService.listAll();
  }
}
