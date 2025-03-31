package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseBatchReq;
import com.example.crazytest.entity.req.ApiDebugReq;
import java.util.List;

public interface ApiCaseRepositoryService extends IService<ApiCase> {

  IPage<ApiCase> list(String tenantId, String name, Long appId, List<Long> pathIds, Boolean status,
      List<Long> allIds, Long ownerId, Integer current, Integer pageSize);

  List<ApiCase> allList(String tenantId);

  Boolean updateApiCase(String remark, Long id);

  Boolean batchOwner(List<Long> ids,Long ownerId);

  Boolean batchUpdate(List<Long> ids,String remark);

  Boolean batchDown(List<Long> ids,String remark);

}
