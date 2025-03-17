package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;

public interface ApplicationManagementService {

  IPage<ApplicationManagement> list(ApplicationManagementReq applicationManagementReq);


  boolean save(ApplicationManagementReq applicationManagementReq);


}
