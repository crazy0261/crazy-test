package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.vo.EnvConfigVO;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.utils.BaseContext;
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
}
