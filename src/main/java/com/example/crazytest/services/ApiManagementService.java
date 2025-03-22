package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;
import java.util.List;

public interface ApiManagementService {

  IPage<ApiManagementVO> listAll(ApiManagementReq apiManagementReq);

  ApiManagement getById(Long id);

  List<Long> getPaths(String path);

}
