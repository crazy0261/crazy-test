package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ApiManagementReq;

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

}
