package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import java.util.List;
import java.util.Objects;
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
  public IPage<ApiCase> list(Long projectId, String name, Long appId, List<Long> pathIds,
      Boolean status, List<Long> allIds, Long ownerId, Integer current, Integer pageSize) {

    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .like(ObjectUtils.isNotNull(name), ApiCase::getName, name)
        .eq(ObjectUtils.isNotNull(appId), ApiCase::getAppId, appId)
        .in(ObjectUtils.isNotNull(pathIds), ApiCase::getApiId, pathIds)
        .in(ObjectUtils.isNotNull(allIds), ApiCase::getId, allIds)
        .eq(ObjectUtils.isNotNull(status), ApiCase::getStatus, status)
        .eq(ObjectUtils.isNotNull(ownerId), ApiCase::getOwnerId, ownerId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiCase::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<ApiCase> allList(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public Boolean updateApiCase(String remark, Long id) {
    return this.lambdaUpdate()
        .set(ApiCase::getRemark, remark)
        .set(ApiCase::getIsDelete, Boolean.TRUE)
        .eq(ApiCase::getId, id)
        .update();
  }

  @Override
  public Boolean batchOwner(List<Long> ids, Long ownerId) {
    return this.lambdaUpdate()
        .set(ApiCase::getOwnerId, ownerId)
        .in(ApiCase::getId, ids)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchUpdate(List<Long> ids, String remark) {
    return this.lambdaUpdate()
        .set(ApiCase::getStatus, Boolean.FALSE)
        .set(ApiCase::getRemark, remark)
        .in(ApiCase::getId, ids)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchDown(List<Long> ids, String remark) {
    return this.lambdaUpdate()
        .set(ApiCase::getStatus, Boolean.TRUE)
        .set(ApiCase::getRemark, remark)
        .in(ApiCase::getId, ids)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Long countCase(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ApiCase> checkApiCaseEnable(List<Long> ids) {
    return this.lambdaQuery()
        .in(ApiCase::getId, ids)
        .in(ApiCase::getStatus, Boolean.FALSE)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<ApiCase> getApiCaseList(Long projectId, Long appId) {
    return this.lambdaQuery()
        .select(ApiCase::getApiId)
        .eq(ApiCase::getProjectId, projectId)
        .eq(ApiCase::getAppId, appId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .groupBy(ApiCase::getApiId)
        .list();
  }

  @Override
  public Long getApiCaseCount(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ApiCase> getApiCaseValid(Long projectId, List<Long> ids) {
    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .in(ApiCase::getId, ids)
        .list();
  }

  @Override
  public Long userApiCaseCount(Long createById) {
    return this.lambdaQuery()
        .eq(ApiCase::getCreateById, createById)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ApiCase> getNotAssetsList(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiCase::getProjectId, projectId)
        .ne(ApiCase::getAsserts, "[]")
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<ApiCase> queryIds(List<Long> ids) {
    return this.lambdaQuery()
        .in(Objects.nonNull(ids), ApiCase::getId, ids)
        .eq(ApiCase::getIsDelete, Boolean.FALSE)
        .list();
  }
}
