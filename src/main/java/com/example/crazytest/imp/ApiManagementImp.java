package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.dto.ApiManagementVo;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.services.ApiManagementService;
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
public class ApiManagementImp implements ApiManagementService {

  @Autowired
  ApiManageRepositoryService apiManageRepository;

  @Override
  public IPage<ApiManagementVo> listAll(ApiManagementReq apiManagementReq) {
    IPage<ApiManagement> apiManagePage = apiManageRepository.listAll(apiManagementReq);

    IPage<ApiManagementVo> apiManagementVoPage = new Page<>();
    apiManagementVoPage.setRecords(apiManagePage.getRecords().stream()
        .map(apiManagement -> {
          // todo 添加应用相关信息，后续添加
          ApiManagementVo apiManageVo = new ApiManagementVo();
          BeanUtils.copyProperties(apiManagement, apiManageVo);
          return apiManageVo;
        }).collect(Collectors.toList()));

    apiManagementVoPage.setTotal(apiManagePage.getTotal());

    return apiManagementVoPage;
  }
}
