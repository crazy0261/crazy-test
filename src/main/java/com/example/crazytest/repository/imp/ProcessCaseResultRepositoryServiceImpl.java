package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.mapper.ProcessCaseResultMapper;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
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
    ServiceImpl<ProcessCaseResultMapper, ProcessCaseRecord> implements
    ProcessCaseResultRepositoryService {

  @Override
  public List<ProcessCaseRecord> list(Long projectId, String status) {
    return this.lambdaQuery()
        .eq(ProcessCaseRecord::getProjectId, projectId)
        .eq(ObjectUtils.isNotNull(status), ProcessCaseRecord::getStatus, status)
        .eq(ProcessCaseRecord::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public ProcessCaseRecord lastResult(Long projectId, Long caseId) {
    return this.lambdaQuery()
        .eq(ProcessCaseRecord::getProjectId, projectId)
        .eq(ProcessCaseRecord::getCaseId, caseId)
        .eq(ProcessCaseRecord::getIsDelete, Boolean.FALSE)
        .orderByDesc(ProcessCaseRecord::getUpdateTime)
        .last("limit 1")
        .one();
  }

  @Override
  public void updateNodes(Long id, String nodes, String status) {
    this.lambdaUpdate()
        .eq(ProcessCaseRecord::getId, id)
        .set(ProcessCaseRecord::getNodes, nodes)
        .set(ProcessCaseRecord::getStatus, status)
        .update();
  }

  @Override
  public List<ProcessCaseRecord> getProcessCaseRecordList(Long processId) {
    return this.lambdaQuery()
        .eq(ProcessCaseRecord::getCaseId, processId)
        .eq(ProcessCaseRecord::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<ProcessCaseRecord> getProcessCaseRecordFailList(Long processId, LocalDateTime time) {
    return this.lambdaQuery()
        .eq(ProcessCaseRecord::getCaseId, processId)
        .ge(ProcessCaseRecord::getCreateTime, time)
        .eq(ProcessCaseRecord::getIsDelete, Boolean.FALSE)
        .orderByAsc(ProcessCaseRecord::getCreateTime)
        .list();
  }

  @Override
  public IPage<ProcessCaseRecord> getProcessCaseRecordLogList(Long caseId, Integer current,
      Integer pageSize) {
    return this.lambdaQuery()
        .eq(ProcessCaseRecord::getCaseId, caseId)
        .orderByDesc(ProcessCaseRecord::getCreateTime)
        .page(new Page<>(current, pageSize));
  }
}
