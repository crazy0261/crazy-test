package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/17 21:24
 * @DESRIPTION
 */

@Service
public class ApplicationManagementServiceImp implements ApplicationManagementService {

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Override
  public IPage<ApplicationManagement> list(ApplicationManagementReq applicationManagementReq) {
    applicationManagementReq.setTenantId(BaseContext.getTenantId());
    return applicationManagementRepositoryService.list(applicationManagementReq);
  }

  @Override
  public boolean save(ApplicationManagementReq applicationManagementReq) {
    ApplicationManagement appManagement = new ApplicationManagement();
    BeanUtils.copyProperties(applicationManagementReq, appManagement);

    return applicationManagementRepositoryService.saveOrUpdate(appManagement);
  }

}
