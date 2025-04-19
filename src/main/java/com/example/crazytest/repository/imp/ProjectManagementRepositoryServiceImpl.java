package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ProjectManagement;
import com.example.crazytest.mapper.ProjectManagementMapper;
import com.example.crazytest.repository.ProjectManagementRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
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
  public IPage<ProjectManagement> list(String name, Integer current, Integer pageSize) {
    return this.lambdaQuery()
        .like(ObjectUtils.isNotNull(name), ProjectManagement::getName, name)
        .eq(ProjectManagement::getIsDelete, Boolean.FALSE)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<ProjectManagement> listAll() {
    return this.lambdaQuery()
        .eq(ProjectManagement::getIsDelete, Boolean.FALSE)
        .list();
  }
}
