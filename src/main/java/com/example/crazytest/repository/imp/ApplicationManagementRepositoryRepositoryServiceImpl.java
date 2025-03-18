package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.mapper.ApplicationManagementMapper;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.utils.BaseContext;
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
  public IPage<ApplicationManagement> list(String name, Long ownerId, int current, int pageSize) {
    return this.lambdaQuery()
        .like(ObjectUtils.isNotNull(name), ApplicationManagement::getName, name)
        .eq(ApplicationManagement::getTenantId, BaseContext.getTenantId())
        .eq(ObjectUtils.isNotNull(ownerId), ApplicationManagement::getOwnerId, ownerId)
        .eq(ApplicationManagement::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApplicationManagement::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

}
