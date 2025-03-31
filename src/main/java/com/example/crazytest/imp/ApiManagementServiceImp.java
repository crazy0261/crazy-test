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
import com.example.crazytest.vo.ApiTypeVO;
import java.util.Arrays;
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
  public Boolean batchUpdateType(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchUpdateType(apiTypeVO.getApiIds(), apiTypeVO.getApiType());
  }

  @Override
  public Boolean batchDelete(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchDelete(apiTypeVO.getApiIds(), apiTypeVO.getRemark());
  }

  @Override
  public Boolean batchMove(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchMove(apiTypeVO.getApiIds(), apiTypeVO.getAppId());
  }

  @Override
  public Boolean batchSetPriority(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchSetPriority(apiTypeVO.getApiIds(), apiTypeVO.getPriority());
  }

  @Override
  public Boolean batchUp(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchUp(apiTypeVO.getApiIds(),apiTypeVO.getRemark());
  }

  @Override
  public Boolean batchDown(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchDown(apiTypeVO.getApiIds(),apiTypeVO.getRemark());
  }

  @Override
  public Boolean batchProd(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchProd(apiTypeVO.getApiIds(), apiTypeVO.getCanProdExec());
  }

  @Override
  public Boolean batchOUpdateOwner(ApiTypeVO apiTypeVO) {
    return apiManageRepository.batchOUpdateOwner(apiTypeVO.getApiIds(), apiTypeVO.getOwnerId());
  }
}
