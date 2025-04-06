package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.crazytest.entity.ProcessCaseResult;
import com.example.crazytest.mapper.ProcessCaseResultMapper;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景用例执行结果 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Service
public class ProcessCaseResultRepositoryServiceImpl extends
    ServiceImpl<ProcessCaseResultMapper, ProcessCaseResult> implements
    ProcessCaseResultRepositoryService {

  @Override
  public List<ProcessCaseResult> list(Long projectId, String status) {
    return this.lambdaQuery()
        .eq(ProcessCaseResult::getProjectId, projectId)
        .eq(ObjectUtils.isNotNull(status), ProcessCaseResult::getStatus, status)
        .eq(ProcessCaseResult::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public ProcessCaseResult lastResult(Long projectId, Long caseId) {
    return this.lambdaQuery()
        .eq(ProcessCaseResult::getProjectId, projectId)
        .eq(ProcessCaseResult::getCaseId, caseId)
        .eq(ProcessCaseResult::getIsDelete, Boolean.FALSE)
        .orderByDesc(ProcessCaseResult::getUpdateTime)
        .last("limit 1")
        .one();
  }
}
