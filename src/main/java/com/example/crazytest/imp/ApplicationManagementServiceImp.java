package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ApplicationManagementVo;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Autowired
  UserRepositoryService userRepositoryService;

  @Override
  public IPage<ApplicationManagementVo> list(String name, Long ownerId, int current, int pageSize) {
    IPage<ApplicationManagement> applicationManagementList = applicationManagementRepositoryService
        .list(name, ownerId, current, pageSize);

    return applicationManagementList.convert(applicationManagement -> {
      ApplicationManagementVo applicationManagementVo = new ApplicationManagementVo();
      BeanUtils.copyProperties(applicationManagement, applicationManagementVo);
      applicationManagementVo.setOwnerName(
          userRepositoryService.getUserData(applicationManagement.getOwnerId()).getName());
      return applicationManagementVo;
    });
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean save(ApplicationManagementReq applicationManagementReq) {
    ApplicationManagement appManagement = new ApplicationManagement();
    applicationManagementReq.setTenantId(BaseContext.getTenantId());
    BeanUtils.copyProperties(applicationManagementReq, appManagement);

    return applicationManagementRepositoryService.saveOrUpdate(appManagement);
  }

}
