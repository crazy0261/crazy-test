package com.example.crazytest.services.imp;

import com.example.crazytest.entity.ProjectUserAssociation;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ProjectUserAssociationRepositoryService;
import com.example.crazytest.services.ProjectUserAssociationService;
import com.example.crazytest.utils.AssertUtil;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/5 12:58
 * @DESRIPTION
 */

@Service
public class ProjectUserAssociationServiceImp implements ProjectUserAssociationService {

  @Autowired
  ProjectUserAssociationRepositoryService projectUserAssociationRepositoryService;

  @Override
  public ProjectUserAssociation getOne(Long userId) {
    ProjectUserAssociation projectUserAssociation = projectUserAssociationRepositoryService
        .getOne(userId);
    AssertUtil.assertTrue(Objects.isNull(projectUserAssociation),
        ResultEnum.USER_PROJECT_NOT_FAIL.getMessage());
    return projectUserAssociation;
  }
}
