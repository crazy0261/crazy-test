package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ApiManagementVo;
import com.example.crazytest.entity.req.ApiManagementReq;

public interface ApiManagementService {

  IPage<ApiManagementVo> listAll(ApiManagementReq apiManagementReq);

}
