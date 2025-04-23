package com.example.crazytest.repository;

import com.example.crazytest.entity.ProjectUserAssociation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目用户关联表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
public interface ProjectUserAssociationRepositoryService extends IService<ProjectUserAssociation> {

  ProjectUserAssociation getOne(Long userId);

}
