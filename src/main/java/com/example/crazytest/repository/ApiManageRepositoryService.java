package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ApiManagementReq;
import java.util.List;

/**
 * <p>
 * 接口管理表 服务类
 * </p>
 *
 * @author author
 * @since 2025-03-13
 */
public interface ApiManageRepositoryService extends IService<ApiManagement> {

  IPage<ApiManagement> listAll(ApiManagementReq apiManagementReq);

  List<ApiManagement> getPaths(Long projectId, String path);

  Boolean batchUpdateType(List<Long> ids, String apiType);

  Boolean batchDelete(List<Long> ids, String remark);

  Boolean batchMove(List<Long> ids, Long appId);

  Boolean batchSetPriority(List<Long> ids, Integer priority);

  Boolean batchUp(List<Long> ids, String remark);

  Boolean batchDown(List<Long> ids, String remark);

  Boolean batchProd(List<Long> ids, Integer canProdExec);

  Boolean batchOUpdateOwner(List<Long> ids, Long ownerId);

  List<ApiManagement> getApiCount(Long projectId, Long appId);

  List<ApiManagement> appApiList(Long projectId, Long appId);

}
