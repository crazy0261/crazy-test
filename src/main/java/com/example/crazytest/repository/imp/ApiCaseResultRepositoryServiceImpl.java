package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.mapper.ApiCaseResultMapper;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
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
  public List<ApiCaseRecord> listResult(Long projectId, String recentExecResult) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .eq(Objects.nonNull(recentExecResult), ApiCaseRecord::getStatus, recentExecResult)
        .list();
  }

  @Override
  public List<ApiCaseRecord> lastExecResult(Long projectId, Long scheduleBatchId) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .eq(ApiCaseRecord::getScheduleBatchId, scheduleBatchId)
        .eq(ApiCaseRecord::getIsDelete, Boolean.FALSE)
        .orderByDesc(ApiCaseRecord::getUpdateTime)
        .list();
  }

  @Override
  public IPage<ApiCaseRecord> resultList(Long projectId, List<Long> ids, Integer current,
      Integer pageSize) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .in(Objects.nonNull(ids), ApiCaseRecord::getId, ids)
        .eq(ApiCaseRecord::getIsDelete, Boolean.FALSE)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<ApiCaseRecord> getResultChildren(Long projectId, Long scheduleBatchId,
      Long apiTestcaseId, Long id) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .eq(ApiCaseRecord::getScheduleBatchId, scheduleBatchId)
        .eq(ApiCaseRecord::getApiTestcaseId, apiTestcaseId)
        .ne(ApiCaseRecord::getId, id)
        .orderByDesc(ApiCaseRecord::getUpdateTime)
        .list();
  }

  @Override
  public List<ApiCaseRecord> getApiCaseCount(Long projectId) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .eq(ApiCaseRecord::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<ApiCaseRecord> getApiCaseFailed(Long projectId, LocalDateTime time) {
    return this.lambdaQuery()
        .eq(ApiCaseRecord::getProjectId, projectId)
        .ge(ApiCaseRecord::getCreateTime, time)
        .orderByAsc(ApiCaseRecord::getCreateTime)
        .list();
  }

  @Override
  public List<ApiCaseRecord> getCountStatusByCaseIds(List<Long> caseIds,Long scheduleBatchId) {
    return this.lambdaQuery()
        .in(ApiCaseRecord::getApiTestcaseId, caseIds)
        .eq(ApiCaseRecord::getScheduleBatchId, scheduleBatchId)
        .eq(ApiCaseRecord::getIsDelete, Boolean.FALSE)
        .list();
  }
}
