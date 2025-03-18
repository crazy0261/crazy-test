package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ApplicationManagementVo;
import com.example.crazytest.entity.req.ApplicationManagementReq;

public interface ApplicationManagementService {

  IPage<ApplicationManagementVo> list(String name, Long ownerId, int current, int pageSize);


  boolean save(ApplicationManagementReq applicationManagementReq);


}
