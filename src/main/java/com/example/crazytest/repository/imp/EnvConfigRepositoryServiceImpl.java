package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.mapper.EnvConfigMapper;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
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
  public IPage<EnvConfig> list(String tenantId, String appid, String name, List<String> domainId,
      int current, int pageSize) {

    return this.lambdaQuery()
        .like(ObjectUtils.isNotNull(name), EnvConfig::getName, name)
        .eq(EnvConfig::getTenantId, tenantId)
        .eq(ObjectUtils.isNotNull(appid), EnvConfig::getAppId, appid)
        .in(ObjectUtils.isNotNull(domainId), EnvConfig::getDomainId, domainId)
        .eq(EnvConfig::getIsDelete, Boolean.FALSE)
        .orderByDesc(EnvConfig::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }
}
