package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.dto.ApiManagementDTO;
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
  public IPage<ApiManagement> listAll(ApiManagementDTO apiManagementDTO) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, BaseContext.getSelectProjectId())
        .like(Objects.nonNull(apiManagementDTO.getName()), ApiManagement::getName,
            apiManagementDTO.getName())
        .eq(Objects.nonNull(apiManagementDTO.getApiType()), ApiManagement::getApiType,
            apiManagementDTO.getApiType())
        .eq(Objects.nonNull(apiManagementDTO.getCanProdExec()), ApiManagement::getCanProdExec,
            apiManagementDTO.getCanProdExec())
        .gt(Objects.nonNull(apiManagementDTO.getCaseCount())
                && Long.parseLong(apiManagementDTO.getCaseCount()) > 0, ApiManagement::getCaseCount,
            apiManagementDTO.getCaseCount())
        .eq(Objects.nonNull(apiManagementDTO.getCaseCount())
                && Long.parseLong(apiManagementDTO.getCaseCount()) == 0, ApiManagement::getCaseCount,
            apiManagementDTO.getCaseCount())
        .gt(Objects.nonNull(apiManagementDTO.getInvokeTimes())
                && Long.parseLong(apiManagementDTO.getCaseCount()) > 0, ApiManagement::getInvokeTimes,
            apiManagementDTO.getInvokeTimes())
        .eq(Objects.nonNull(apiManagementDTO.getInvokeTimes())
                && Long.parseLong(apiManagementDTO.getInvokeTimes()) == 0,
            ApiManagement::getInvokeTimes,
            apiManagementDTO.getInvokeTimes())
        .eq(Objects.nonNull(apiManagementDTO.getOwnerId()), ApiManagement::getOwnerId,
            apiManagementDTO.getOwnerId())
        .eq(Objects.nonNull(apiManagementDTO.getStatus()), ApiManagement::getStatus,
            apiManagementDTO.getStatus())
        .eq(Objects.nonNull(apiManagementDTO.getApplicationId()),
            ApiManagement::getApplicationId, apiManagementDTO.getApplicationId())
        .eq(Objects.nonNull(apiManagementDTO.getPriority()), ApiManagement::getPriority,
            apiManagementDTO.getPriority())
        .like(Objects.nonNull(apiManagementDTO.getPath()), ApiManagement::getPath,
            apiManagementDTO.getPath())
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiManagement::getUpdateTime)
        .page(new Page<>(apiManagementDTO.getCurrent(), apiManagementDTO.getPageSize()));

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
  public List<ApiManagement> getApiCount(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .eq(ApiManagement::getApplicationId, appId)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<ApiManagement> appApiList(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .eq(ApiManagement::getApplicationId, appId)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public Long getApiCount(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiManagement::getProjectId, projectId)
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .count();
  }

}
