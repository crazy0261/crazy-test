package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ProcessCaseNodeResult;
import com.example.crazytest.mapper.ProcessCaseNodeResultMapper;
import com.example.crazytest.repository.ProcessCaseNodeResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景用例节点执行结果 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Service
public class ProcessCaseNodeResultRepositoryServiceImpl extends
    ServiceImpl<ProcessCaseNodeResultMapper, ProcessCaseNodeResult> implements
    ProcessCaseNodeResultRepositoryService {

  @Override
  public ProcessCaseNodeResult findLast(Long projectId, Long resultId) {
    return this.lambdaQuery()
        .eq(ProcessCaseNodeResult::getProjectId, projectId)
        .eq(ProcessCaseNodeResult::getCaseResultId, resultId)
        .isNotNull(ProcessCaseNodeResult::getOutputParams)
        .eq(ProcessCaseNodeResult::getIsDelete, Boolean.FALSE)
        .orderByDesc(ProcessCaseNodeResult::getUpdateTime)
        .last("limit 1")
        .one();
  }

  @Override
  public ProcessCaseNodeResult getDetail(Long resultId, Long nodeId) {
    return this.lambdaQuery()
        .eq(ProcessCaseNodeResult::getCaseResultId, resultId)
        .eq(ProcessCaseNodeResult::getNodeId, nodeId)
        .one();
  }
}
