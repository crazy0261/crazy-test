package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.mapper.ApiCaseResultMapper;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口执行结果 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
@Service
public class ApiCaseResultRepositoryServiceImpl extends
    ServiceImpl<ApiCaseResultMapper, ApiCaseResult> implements
    ApiCaseResultRepositoryService {

  @Override
  public IPage<ApiCaseResult> list(String apiTestcaseId, Integer current, Integer pageSize) {

    return this.lambdaQuery()
        .eq(ApiCaseResult::getApiTestcaseId, apiTestcaseId)
        .orderByDesc(ApiCaseResult::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public ApiCaseResult lastApiCaseResult(Long apiTestcaseId) {
    return this.lambdaQuery()
        .eq(ApiCaseResult::getApiTestcaseId, apiTestcaseId)
        .orderByDesc(ApiCaseResult::getUpdateTime)
        .last("limit 1")
        .one();

  }

  @Override
  public List<ApiCaseResult> listResult(String tenantId, String recentExecResult) {
    return this.lambdaQuery()
        .eq(ApiCaseResult::getTenantId, tenantId)
        .eq(Objects.nonNull(recentExecResult), ApiCaseResult::getStatus, recentExecResult)
        .list();
  }
}
