package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProjectManagement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目管理 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
public interface ProjectManagementRepositoryService extends IService<ProjectManagement> {

  IPage<ProjectManagement> list(String name, Integer current, Integer pageSize);
}
