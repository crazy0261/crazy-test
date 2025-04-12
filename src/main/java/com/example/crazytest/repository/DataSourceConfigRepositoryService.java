package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DataSourceConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据库配置 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-08
 */
public interface DataSourceConfigRepositoryService extends IService<DataSourceConfig> {

  DataSourceConfig getDatabaseConfig(Long projectId, Long appId);

  Long getCountByProjectIdNameCount(Long projectId, String name);

  IPage<DataSourceConfig> list(Long projectId, String name, Integer current, Integer pageSize);


}
