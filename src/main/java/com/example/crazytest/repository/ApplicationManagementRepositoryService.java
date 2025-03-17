package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import java.util.List;

/**
 * <p>
 * 应用管理 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-17
 */
public interface ApplicationManagementRepositoryService extends IService<ApplicationManagement> {

  IPage<ApplicationManagement> list(ApplicationManagementReq applicationManagementReq);

}
