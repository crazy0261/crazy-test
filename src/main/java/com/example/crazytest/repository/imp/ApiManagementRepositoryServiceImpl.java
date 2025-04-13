package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.mapper.ApiManagementMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口管理表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-13
 */
@Service
public class ApiManagementRepositoryServiceImpl extends
    ServiceImpl<ApiManagementMapper, ApiManagement> implements
    ApiManageRepositoryService {

  @Autowired
  ApiManagementMapper apiManageMapper;


  @Override
  public IPage<ApiManagement> listAll(ApiManagementReq apiManagementReq) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, BaseContext.getSelectProjectId())
        .like(Objects.nonNull(apiManagementReq.getName()), ApiManagement::getName,
            apiManagementReq.getName())
        .eq(Objects.nonNull(apiManagementReq.getApiType()), ApiManagement::getApiType,
            apiManagementReq.getApiType())
        .eq(Objects.nonNull(apiManagementReq.getCanProdExec()), ApiManagement::getCanProdExec,
            apiManagementReq.getCanProdExec())
        .gt(Objects.nonNull(apiManagementReq.getCaseCount())
                && Long.parseLong(apiManagementReq.getCaseCount()) > 0, ApiManagement::getCaseCount,
            apiManagementReq.getCaseCount())
        .eq(Objects.nonNull(apiManagementReq.getCaseCount())
                && Long.parseLong(apiManagementReq.getCaseCount()) == 0, ApiManagement::getCaseCount,
            apiManagementReq.getCaseCount())
        .gt(Objects.nonNull(apiManagementReq.getInvokeTimes())
                && Long.parseLong(apiManagementReq.getCaseCount()) > 0, ApiManagement::getInvokeTimes,
            apiManagementReq.getInvokeTimes())
        .eq(Objects.nonNull(apiManagementReq.getInvokeTimes())
                && Long.parseLong(apiManagementReq.getInvokeTimes()) == 0,
            ApiManagement::getInvokeTimes,
            apiManagementReq.getInvokeTimes())
        .eq(Objects.nonNull(apiManagementReq.getOwnerId()), ApiManagement::getOwnerId,
            apiManagementReq.getOwnerId())
        .eq(Objects.nonNull(apiManagementReq.getStatus()), ApiManagement::getStatus,
            apiManagementReq.getStatus())
        .eq(Objects.nonNull(apiManagementReq.getApplicationId()),
            ApiManagement::getApplicationId, apiManagementReq.getApplicationId())
        .eq(Objects.nonNull(apiManagementReq.getPriority()), ApiManagement::getPriority,
            apiManagementReq.getPriority())
        .like(Objects.nonNull(apiManagementReq.getPath()), ApiManagement::getPath,
            apiManagementReq.getPath())
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiManagement::getUpdateTime)
        .page(new Page<>(apiManagementReq.getCurrent(), apiManagementReq.getPageSize()));

  }

  @Override
  public List<ApiManagement> getPaths(Long projectId, String path) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .like(ObjectUtils.isNotNull(path), ApiManagement::getPath, path).list();
  }

  @Override
  public Boolean batchUpdateType(List<Long> ids, String apiType) {
    return this.lambdaUpdate()
        .set(ApiManagement::getApiType, apiType)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .in(ApiManagement::getId, ids)
        .update();
  }

  @Override
  public Boolean batchDelete(List<Long> ids, String remark) {
    return this.lambdaUpdate()
        .set(ApiManagement::getRemark, remark)
        .set(ApiManagement::getIsDelete, Boolean.TRUE)
        .in(ApiManagement::getId, ids)
        .update();
  }

  @Override
  public Boolean batchMove(List<Long> ids, Long appId) {
    return this.lambdaUpdate()
        .set(ApiManagement::getApplicationId, appId)
        .in(ApiManagement::getId, ids)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchSetPriority(List<Long> ids, Integer priority) {
    return this.lambdaUpdate()
        .in(ApiManagement::getId, ids)
        .set(ApiManagement::getPriority, priority)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchUp(List<Long> ids, String remark) {
    return this.lambdaUpdate()
        .set(ApiManagement::getStatus, Boolean.FALSE)
        .set(ApiManagement::getRemark, remark)
        .in(ApiManagement::getId, ids)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchDown(List<Long> ids, String remark) {
    return this.lambdaUpdate()
        .set(ApiManagement::getStatus, Boolean.TRUE)
        .set(ApiManagement::getRemark, remark)
        .in(ApiManagement::getId, ids)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchProd(List<Long> ids, Integer canProdExec) {
    return this.lambdaUpdate()
        .set(ApiManagement::getCanProdExec, canProdExec)
        .in(ApiManagement::getId, ids)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Boolean batchOUpdateOwner(List<Long> ids, Long ownerId) {
    return this.lambdaUpdate()
        .set(ApiManagement::getOwnerId, ownerId)
        .in(ApiManagement::getId, ids)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .update();
  }

  @Override
  public Long getApiCount(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .eq(ApiManagement::getApplicationId, appId)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ApiManagement> appApiList(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .eq(ApiManagement::getApplicationId, appId)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .list();
  }

}
