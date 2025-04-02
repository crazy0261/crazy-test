package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.mapper.ApiCaseResultMapper;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
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
    ServiceImpl<ApiCaseResultMapper, ApiCaseRecord> implements
    ApiCaseResultRepositoryService {

  @Override
  public IPage<ApiCaseRecord> list(String apiTestcaseId, Integer current, Integer pageSize) {

    return this.lambdaQuery()
        .eq(ApiCaseRecord::getApiTestcaseId, apiTestcaseId)
        .orderByDesc(ApiCaseRecord::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public ApiCaseRecord lastApiCaseResult(Long apiTestcaseId) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getApiTestcaseId, apiTestcaseId)
        .orderByDesc(ApiCaseRecord::getUpdateTime)
        .last("limit 1")
        .one();

  }

  @Override
  public List<ApiCaseRecord> listResult(String tenantId, String recentExecResult) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getTenantId, tenantId)
        .eq(Objects.nonNull(recentExecResult), ApiCaseRecord::getStatus, recentExecResult)
        .list();
  }
}
