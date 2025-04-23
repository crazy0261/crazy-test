package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ProjectUserAssociation;
import com.example.crazytest.mapper.ProjectUserAssociationMapper;
import com.example.crazytest.repository.ProjectUserAssociationRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目用户关联表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
@Service
public class ProjectUserAssociationRepositoryServiceImpl extends
    ServiceImpl<ProjectUserAssociationMapper, ProjectUserAssociation> implements
    ProjectUserAssociationRepositoryService {

  @Override
  public ProjectUserAssociation getOne(Long userId) {
    return this.lambdaQuery()
        .eq(ProjectUserAssociation::getUserId, userId)
        .eq(ProjectUserAssociation::getIsDelete, Boolean.FALSE)
        .eq(ProjectUserAssociation::getStatus, Boolean.FALSE)
        .orderByDesc(ProjectUserAssociation::getUpdateTime)
        .last("limit 1")
        .one();
  }
}
