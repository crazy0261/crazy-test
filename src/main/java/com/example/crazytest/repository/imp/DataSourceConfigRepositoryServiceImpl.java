package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.DataSourceConfig;
import com.example.crazytest.mapper.DataSourceConfigMapper;
import com.example.crazytest.repository.DataSourceConfigRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库配置 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-08
 */
@Service
public class DataSourceConfigRepositoryServiceImpl extends
    ServiceImpl<DataSourceConfigMapper, DataSourceConfig> implements
    DataSourceConfigRepositoryService {

  @Override
  public DataSourceConfig getDatabaseConfig(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(DataSourceConfig::getProjectId, projectId)
        .eq(DataSourceConfig::getAppId, appId)
        .eq(DataSourceConfig::getIsDelete, Boolean.FALSE)
        .one();
  }

  @Override
  public Long getCountByProjectIdNameCount(Long projectId, String name) {
    return this.lambdaQuery()
        .eq(DataSourceConfig::getProjectId, projectId)
        .eq(DataSourceConfig::getName, name)
        .eq(DataSourceConfig::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public IPage<DataSourceConfig> list(Long projectId, String name,
      Integer current, Integer pageSize) {
    return this.lambdaQuery()
        .eq(DataSourceConfig::getProjectId, projectId)
        .like(Objects.nonNull(name), DataSourceConfig::getName, name)
        .eq(DataSourceConfig::getIsDelete, Boolean.FALSE)
        .orderByDesc(DataSourceConfig::getUpdateTime)
        .page(new Page<>(current, pageSize));

  }
}
