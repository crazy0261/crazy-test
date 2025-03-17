package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.mapper.ApplicationManagementMapper;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用管理 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-17
 */
@Service
public class ApplicationManagementRepositoryRepositoryServiceImpl extends
    ServiceImpl<ApplicationManagementMapper, ApplicationManagement> implements
    ApplicationManagementRepositoryService {

  @Override
  public IPage<ApplicationManagement> list(ApplicationManagementReq applicationManagementReq) {
    return this.lambdaQuery()
        .like(StringUtils.isNotEmpty(applicationManagementReq.getName()),
            ApplicationManagement::getName, applicationManagementReq.getName())
        .eq(StringUtils.isNotEmpty(applicationManagementReq.getTenantId()), ApplicationManagement::getTenantId,applicationManagementReq.getTenantId())
        .eq(ObjectUtils.isNotNull(applicationManagementReq.getOwnerId()), ApplicationManagement::getOwnerId,applicationManagementReq.getOwnerId())
        .eq( ApplicationManagement::getIsDelete,0)
        .orderByDesc(ApplicationManagement::getUpdateTime)
        .page(new Page<>(applicationManagementReq.getCurrent(), applicationManagementReq.getPageSize()));
  }

}
