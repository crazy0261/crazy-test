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

  IPage<EnvConfig> list(Long projectId, String appid, String name, String sort,
      List<String> domainId,
      int current, int pageSize);

  EnvConfig getByAppId(Long projectId, Long appId, Long envId);

  List<EnvConfig> getEnvConfigByAppIdAndDomainId(Long appId, Long domainId);

  List<EnvConfig> listAll();

  Integer getLastEnvId(Long projectId, Long appId);

  Integer getLastSortValue(Long projectId, Long appId);

  EnvConfig getEnvConfig(Long projectId, Long appId);

  List<EnvConfig> envAppList(Long projectId, Long appId);

  List<EnvConfig> envList(Long projectId);


}
