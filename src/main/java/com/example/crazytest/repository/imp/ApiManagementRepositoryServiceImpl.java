package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.mapper.ApiManagementMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ApiTypeVO;
import java.util.List;
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
        .eq(ApiManagement::getTenantId, BaseContext.getTenantId())
        .like(ObjectUtils.isNotNull(apiManagementReq.getName()), ApiManagement::getName,
            apiManagementReq.getName())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getApiType()), ApiManagement::getApiType,
            apiManagementReq.getApiType())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getCanProdExec()), ApiManagement::getCanProdExec,
            apiManagementReq.getCanProdExec())
        .gt(ObjectUtils.isNotNull(apiManagementReq.getCaseCount())
                && Long.parseLong(apiManagementReq.getCaseCount()) > 0, ApiManagement::getCaseCount,
            apiManagementReq.getCaseCount())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getCaseCount())
                && Long.parseLong(apiManagementReq.getCaseCount()) == 0, ApiManagement::getCaseCount,
            apiManagementReq.getCaseCount())
        .gt(ObjectUtils.isNotNull(apiManagementReq.getInvokeTimes())
                && Long.parseLong(apiManagementReq.getCaseCount()) > 0, ApiManagement::getInvokeTimes,
            apiManagementReq.getInvokeTimes())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getInvokeTimes())
                && Long.parseLong(apiManagementReq.getInvokeTimes()) == 0,
            ApiManagement::getInvokeTimes,
            apiManagementReq.getInvokeTimes())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getOwnerId()), ApiManagement::getOwnerId,
            apiManagementReq.getOwnerId())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getStatus()), ApiManagement::getStatus,
            apiManagementReq.getStatus())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getApplicationId()),
            ApiManagement::getApplicationId, apiManagementReq.getApplicationId())
        .eq(ObjectUtils.isNotNull(apiManagementReq.getPriority()), ApiManagement::getPriority,
            apiManagementReq.getPriority())
        .like(ObjectUtils.isNotNull(apiManagementReq.getPath()), ApiManagement::getPath,
            apiManagementReq.getPath())
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiManagement::getUpdateTime)
        .page(new Page<>(apiManagementReq.getCurrent(), apiManagementReq.getPageSize()));

  }

  @Override
  public List<ApiManagement> getPaths(String tenantId, String path) {
    return this.lambdaQuery()
        .eq(ApiManagement::getTenantId, tenantId)
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

}
