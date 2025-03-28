package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.mapper.ApiCaseResultMapper;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
