package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.mapper.EnvConfigMapper;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 环境信息表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-19
 */
@Service
public class EnvConfigRepositoryServiceImpl extends
    ServiceImpl<EnvConfigMapper, EnvConfig> implements
    EnvConfigRepositoryService {

  @Override
  public IPage<EnvConfig> list(Long projectId, String appid, String name, String sort,
      List<String> domainId, int current, int pageSize) {

    return this.lambdaQuery()
        .like(ObjectUtils.isNotNull(name), EnvConfig::getEnvName, name)
        .eq(EnvConfig::getProjectId, projectId)
        .eq(ObjectUtils.isNotNull(appid), EnvConfig::getAppId, appid)
        .in(EnvConfig::getDomainId, domainId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .orderByAsc(EnvConfig::getAppId)
        .orderByAsc(ObjectUtils.isNull(sort) || "ascend".equals(sort), EnvConfig::getEnvSort)
        .orderByDesc(ObjectUtils.isNotNull(sort) && "descend".equals(sort), EnvConfig::getEnvSort)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public EnvConfig getByAppId(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getAppId, appId)
        .eq(EnvConfig::getProjectId, projectId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .last("limit 1")
        .one();
  }

  @Override
  public List<EnvConfig> getEnvConfigByAppIdAndDomainId(Long appId, Long domainId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getAppId, appId)
        .eq(EnvConfig::getDomainId, domainId)
        .list();
  }

  @Override
  public List<EnvConfig> listAll() {
    return this.lambdaQuery()
        .eq(EnvConfig::getProjectId, BaseContext.getSelectProjectId())
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public Integer getLastEnvId(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getProjectId, projectId)
        .eq(EnvConfig::getAppId, appId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .orderByDesc(EnvConfig::getEnvId)
        .last("limit 1")
        .one()
        .getEnvId();
  }

  @Override
  public Integer getLastSortValue(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getProjectId, projectId)
        .eq(EnvConfig::getAppId, appId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .orderByDesc(EnvConfig::getEnvSort)
        .last("limit 1")
        .one()
        .getEnvSort();
  }

  @Override
  public EnvConfig getEnvConfig(Long projectId, Long appId, Long envId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getProjectId, projectId)
        .eq(EnvConfig::getAppId, appId)
        .eq(EnvConfig::getEnvId, envId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .last("limit 1")
        .one();
  }

  @Override
  public List<EnvConfig> envAppList(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(EnvConfig::getProjectId, projectId)
        .eq(Objects.nonNull(appId),EnvConfig::getAppId, appId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .list();
  }
}
