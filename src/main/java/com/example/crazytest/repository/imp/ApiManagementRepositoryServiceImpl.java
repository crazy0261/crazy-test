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
    LambdaQueryWrapper<ApiManagement> wrapper = new LambdaQueryWrapper<ApiManagement>()
        .eq(ApiManagement::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiManagement::getUpdateTime);
//        .like(StringUtils.isNotEmpty(apiManagementReq.getAccount()), ApiManagement::getAccount, apiManagementReq.getAccount())
//        .like(StringUtils.isNotEmpty(apiManagementReq.getName()), ApiManagement::getName, apiManagementReq.getName())
//        .eq(StringUtils.isNotEmpty(apiManagementReq.getMethod()), ApiManagement::getMethod, apiManagementReq.getMethod())
//       .eq(StringUtils.isNotEmpty())

    return apiManageMapper
        .selectPage(new Page<>(apiManagementReq.getCurrent(), apiManagementReq.getPageSize()),
            wrapper);
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
}
