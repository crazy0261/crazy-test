package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;

public interface ApiManagementService {

  IPage<ApiManagementVO> listAll(ApiManagementReq apiManagementReq);

  ApiManagement getById(Long id);

}
