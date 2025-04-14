package com.example.crazytest.services.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.vo.ApplicationManagementVO;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.req.ApplicationManagementReq;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @name Menghui
 * @date 2025/3/17 21:24
 * @DESRIPTION
 */

@Service
public class ApplicationManagementServiceImp implements ApplicationManagementService {

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  ApiManageRepositoryService apiManageRepositoryService;

  @Autowired
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Override
  public IPage<ApplicationManagementVO> list(String name, Long ownerId, int current, int pageSize) {
    IPage<ApplicationManagement> applicationManagementList = applicationManagementRepositoryService
        .list(name, ownerId, current, pageSize);

    return applicationManagementList.convert(applicationManagement -> {
      ApplicationManagementVO applicationManagementVo = new ApplicationManagementVO();
      Long apiCount = apiManageRepositoryService
          .getApiCount(applicationManagement.getProjectId(), applicationManagement.getId());
      BeanUtils.copyProperties(applicationManagement, applicationManagementVo);
      applicationManagementVo.setOwnerName(
          userRepositoryService.getUserData(applicationManagement.getOwnerId()).getName());
      applicationManagementVo.setApiCount(apiCount);
      applicationManagementVo.setCoverApiCount(
          getCoverApiCount(applicationManagement.getProjectId(), applicationManagement.getId(),
              apiCount));
      return applicationManagementVo;
    });
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean save(ApplicationManagementReq applicationManagementReq) {
    ApplicationManagement appManagement = new ApplicationManagement();
    applicationManagementReq.setProjectId(BaseContext.getSelectProjectId());
    BeanUtils.copyProperties(applicationManagementReq, appManagement);

    return applicationManagementRepositoryService.saveOrUpdate(appManagement);
  }

  @Override
  public ApplicationManagement getById(Long id) {
    return applicationManagementRepositoryService.getById(id);
  }

  @Override
  public List<ApplicationManagement> listAllApplicationManagement() {
    return applicationManagementRepositoryService
        .listAllApplicationManagement(BaseContext.getSelectProjectId());
  }

  @Override
  public Long getCoverApiCount(Long projectId, Long appId, Long apiCount) {

    return apiCount - apiCaseRepositoryService.getApiCaseList(projectId, appId);
  }

}
