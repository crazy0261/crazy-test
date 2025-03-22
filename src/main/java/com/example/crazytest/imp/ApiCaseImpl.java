package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.User;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.services.ApplicationManagementService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ApiCaseVO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Service
public class ApiCaseImpl extends ServiceImpl<ApiCaseMapper, ApiCase> implements
    ApiCaseService {

  @Autowired
  ApiCaseRepositoryService apiCaseRepository;

  @Autowired
  ApiManagementService apiManagementService;

  @Autowired
  ApplicationManagementService applicationManagementService;

  @Autowired
  UserService userService;

  @Override
  public IPage<ApiCaseVO> list(String name, Long appId, String path, Boolean status,
      String recentExecResult, Long ownerId, Integer current, Integer pageSize) {

    List<Long> pathIds = apiManagementService.getPaths(path);

    IPage<ApiCase> apiCaseIPage = apiCaseRepository
        .list(BaseContext.getTenantId(), name, appId, pathIds, status, recentExecResult, ownerId,
            current, pageSize);

    return apiCaseIPage.convert(apiCase -> {
      ApiCaseVO apiCaseVO = new ApiCaseVO();
      BeanUtils.copyProperties(apiCase, apiCaseVO);
      apiCaseRepository.getById(apiCase.getAppId());

      User user = userService.getById(apiCase.getOwnerId());
      apiCaseVO.setAppName(applicationManagementService.getById(apiCase.getAppId()).getName());
      apiCaseVO.setPath(apiManagementService.getById(apiCase.getApiId()).getPath());
      apiCaseVO.setOwnerName(Optional.ofNullable(user).map(User::getName).orElse(""));
      return apiCaseVO;
    });
  }

  @Override
  public ApiCase getById(Long id) {
    return apiCaseRepository.getById(id);

  }

  @Override
  public boolean save(ApiCase apiCase) {
    apiCase.setTenantId(BaseContext.getTenantId());
    return apiCaseRepository.saveOrUpdate(apiCase);
  }
}
