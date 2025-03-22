package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.ApiCase;
import java.util.List;

public interface ApiCaseRepositoryService extends IService<ApiCase> {

  IPage<ApiCase> list(String tenantId, String name, Long appId, List<Long> pathIds, Boolean status,
      String recentExecResult, Long ownerId, Integer current, Integer pageSize);

}
