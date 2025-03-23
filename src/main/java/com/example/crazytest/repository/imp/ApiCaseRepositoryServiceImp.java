package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 14:42
 * @DESRIPTION
 */

@Service
public class ApiCaseRepositoryServiceImp extends ServiceImpl<ApiCaseMapper, ApiCase> implements
    ApiCaseRepositoryService {

  @Autowired
  ApiCaseMapper apiCaseMapper;

  @Override
  public IPage<ApiCase> list(String tenantId, String name, Long appId, List<Long> pathIds,
      Boolean status, String recentExecResult, Long ownerId, Integer current, Integer pageSize) {

    return this.lambdaQuery()
        .eq(ApiCase::getTenantId, tenantId)
        .like(ObjectUtils.isNotNull(name), ApiCase::getName, name)
        .eq(ObjectUtils.isNotNull(appId), ApiCase::getAppId, appId)
        .in(ObjectUtils.isNotNull(pathIds), ApiCase::getApiId, pathIds)
        .eq(ObjectUtils.isNotNull(status), ApiCase::getStatus, status)
        .eq(ObjectUtils.isNotNull(recentExecResult), ApiCase::getRecentExecResult, recentExecResult)
        .eq(ObjectUtils.isNotNull(ownerId), ApiCase::getOwnerId, ownerId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiCase::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<ApiCase> allList(String tenantId) {
    return this.lambdaQuery()
        .eq(ApiCase::getTenantId, tenantId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .list();
  }
}
