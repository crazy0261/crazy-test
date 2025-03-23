package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 环境信息表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-19
 */
public interface EnvConfigRepositoryService extends IService<EnvConfig> {

  IPage<EnvConfig> list(String tenantId, String appid, String name, List<String> domainId,
      int current, int pageSize);

  List<EnvConfig> getEnvConfigByName(String tenantId, String name);

  EnvConfig getEnvName(Long id);

  EnvConfig getByAppId(Long appId);

}
