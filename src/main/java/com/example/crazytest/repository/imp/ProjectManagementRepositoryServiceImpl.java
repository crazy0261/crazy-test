package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.entity.req.ProjectManagementReq;
import com.example.crazytest.mapper.ProjectManagementMapper;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目管理 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Service
public class ProjectManagementRepositoryServiceImpl extends
    ServiceImpl<ProjectManagementMapper, ProjectManagement> implements
    ProjectManagementRepositoryService {

  @Autowired
  ProjectManagementMapper projectMapper;

  @Override
  public IPage<ProjectManagement> list(ProjectManagementReq projectManagementReq) {
    return this.lambdaQuery()
        .like(StringUtils.isNotEmpty(projectManagementReq.getName()), ProjectManagement::getName,
            projectManagementReq.getName())
        .eq(ProjectManagement::getIsDelete, Boolean.FALSE)
        .page(new Page<>(projectManagementReq.getCurrent(), projectManagementReq.getPageSize()));
  }
}
