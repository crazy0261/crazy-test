package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.vo.ApiTypeReq;
import java.util.List;

public interface ApiManagementService {

  IPage<ApiManagementVO> listAll(ApiManagementReq apiManagementReq);

  ApiManagement getById(Long id);

  List<Long> getPaths(String path);

  Boolean save(ApiManagement apiManagement);

  Boolean batchUpdateType(ApiTypeReq apiTypeReq);

  Boolean batchDelete(ApiTypeReq apiTypeReq);

  Boolean batchMove(ApiTypeReq apiTypeReq);


}
