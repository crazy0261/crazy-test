package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.User;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.entity.req.ApiTypeReq;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 12:02
 * @DESRIPTION
 */

@Service
public class ApiManagementServiceImp implements ApiManagementService {

  @Autowired
  ApiManageRepositoryService apiManageRepository;

  @Autowired
  ApplicationManagementService applicationManagementService;

  @Autowired
  UserService userService;

  @Override
  public IPage<ApiManagementVO> listAll(ApiManagementReq apiManagementReq) {
    IPage<ApiManagement> apiManagePage = apiManageRepository.listAll(apiManagementReq);

    return apiManagePage.convert(apiManagement -> {
      ApiManagementVO apiManageVo = new ApiManagementVO();

      ApplicationManagement applicationManagement = applicationManagementService
          .getById(apiManagement.getApplicationId());
      User user = userService.getById(apiManagement.getOwnerId());
      BeanUtils.copyProperties(apiManagement, apiManageVo);
      apiManageVo.setApplicationName(applicationManagement.getName());
      apiManageVo.setOwnerName(user.getName());
      return apiManageVo;
    });
  }

  @Override
  public ApiManagement getById(Long id) {
    return apiManageRepository.getById(id);
  }

  @Override
  public List<Long> getPaths(String path) {
    List<ApiManagement> apiManagements = apiManageRepository
        .getPaths(BaseContext.getTenantId(), path);
    return apiManagements.stream().map(ApiManagement::getId).collect(Collectors.toList());
  }

  @Override
  public Boolean save(ApiManagement apiManagement) {
    apiManagement.setTenantId(
        Objects.nonNull(apiManagement.getTenantId()) ? apiManagement.getTenantId()
            : BaseContext.getTenantId());
    return apiManageRepository.saveOrUpdate(apiManagement);
  }

  @Override
  public Boolean batchUpdateType(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchUpdateType(apiTypeReq.getApiIds(), apiTypeReq.getApiType());
  }

  @Override
  public Boolean batchDelete(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchDelete(apiTypeReq.getApiIds(), apiTypeReq.getRemark());
  }

  @Override
  public Boolean batchMove(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchMove(apiTypeReq.getApiIds(), apiTypeReq.getAppId());
  }

  @Override
  public Boolean batchSetPriority(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchSetPriority(apiTypeReq.getApiIds(), apiTypeReq.getPriority());
  }

  @Override
  public Boolean batchUp(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchUp(apiTypeReq.getApiIds(), apiTypeReq.getRemark());
  }

  @Override
  public Boolean batchDown(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchDown(apiTypeReq.getApiIds(), apiTypeReq.getRemark());
  }

  @Override
  public Boolean batchProd(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchProd(apiTypeReq.getApiIds(), apiTypeReq.getCanProdExec());
  }

  @Override
  public Boolean batchOUpdateOwner(ApiTypeReq apiTypeReq) {
    return apiManageRepository.batchOUpdateOwner(apiTypeReq.getApiIds(), apiTypeReq.getOwnerId());
  }
}
