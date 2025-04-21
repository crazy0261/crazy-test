package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.ApiCase;
import java.util.List;

public interface ApiCaseRepositoryService extends IService<ApiCase> {

  IPage<ApiCase> list(Long projectId, String name, Long appId, List<Long> pathIds, Boolean status,
      List<Long> allIds, Long ownerId, Integer current, Integer pageSize);

  List<ApiCase> allList(Long projectId);

  Boolean updateApiCase(String remark, Long id);

  Boolean batchOwner(List<Long> ids, Long ownerId);

  Boolean batchUpdate(List<Long> ids, String remark);

  Boolean batchDown(List<Long> ids, String remark);

  Long countCase(Long projectId);

  List<ApiCase> checkApiCaseEnable(List<Long> ids);

  List<ApiCase> getApiCaseList(Long projectId, Long appId);

  Long getApiCaseCount(Long projectId);

  List<ApiCase> getApiCaseValid(Long projectId,List<Long> ids);

  Long userApiCaseCount(Long createById);

  List<ApiCase> getNotAssetsList(Long projectId);

}
