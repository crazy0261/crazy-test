package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.vo.ApplicationManagementVO;
import com.example.crazytest.entity.req.ApplicationManagementReq;

public interface ApplicationManagementService {

  IPage<ApplicationManagementVO> list(String name, Long ownerId, int current, int pageSize);


  boolean save(ApplicationManagementReq applicationManagementReq);

  ApplicationManagement getById(Long id);


}
