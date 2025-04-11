package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.vo.ApplicationManagementVO;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import java.util.List;

public interface ApplicationManagementService {

  IPage<ApplicationManagementVO> list(String name, Long ownerId, int current, int pageSize);


  boolean save(ApplicationManagementReq applicationManagementReq);

  ApplicationManagement getById(Long id);

  List<ApplicationManagement> listAllApplicationManagement();

  Long getCoverApiCount(Long projectId, Long appId, Long apiCount);

}
