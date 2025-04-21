package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import com.example.crazytest.mapper.ProcessCaseMapper;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Service
public class ProcessCaseRepositoryServiceImpl extends
    ServiceImpl<ProcessCaseMapper, ProcessCase> implements
    ProcessCaseRepositoryService {

  @Override
  public IPage<ProcessCase> listPage(ProcessCaseDTO processCaseDTO, Long projectId,
      List<Long> ids) {
    return this.lambdaQuery()
        .eq(ObjectUtils.isNotNull(processCaseDTO.getTreeKey()), ProcessCase::getTreeKey,
            processCaseDTO.getTreeKey())
        .eq(ProcessCase::getProjectId, projectId)
        .in(ObjectUtils.isNotNull(ids), ProcessCase::getId, ids)
        .like(ObjectUtils.isNotNull(processCaseDTO.getName()), ProcessCase::getName,
            processCaseDTO.getName())
        .eq(ObjectUtils.isNotNull(processCaseDTO.getOwnerId()), ProcessCase::getOwnerId,
            processCaseDTO.getOwnerId())
        .eq(ObjectUtils.isNotNull(processCaseDTO.getStatus()), ProcessCase::getStatus,
            processCaseDTO.getStatus())
        .eq(ObjectUtils.isNotNull(processCaseDTO.getIsSubProcess()), ProcessCase::getIsSubProcess,
            processCaseDTO.getIsSubProcess())
        .orderByDesc(ProcessCase::getUpdateTime)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .page(new Page<>(processCaseDTO.getCurrent(), processCaseDTO.getPageSize()));
  }

  @Override
  public Boolean batchUpdateOwner(ProcessCaseBatchReq processCaseBatchReq, Long projectId) {
    return this.lambdaUpdate()
        .eq(ProcessCase::getProjectId, projectId)
        .in(ProcessCase::getId, processCaseBatchReq.getCaseIds())
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .set(ProcessCase::getOwnerId, processCaseBatchReq.getOwnerId())
        .update();
  }

  @Override
  public Boolean batchUpdateMove(ProcessCaseBatchReq processCaseBatchReq, Long projectId) {
    return this.lambdaUpdate()
        .eq(ProcessCase::getProjectId, projectId)
        .in(ProcessCase::getId, processCaseBatchReq.getCaseIds())
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .set(ProcessCase::getTreeKey, processCaseBatchReq.getTreeKey())
        .update();
  }

  @Override
  public Boolean batchUpdateUpCase(ProcessCaseBatchReq processCaseBatchReq, Long projectId) {
    return this.lambdaUpdate()
        .eq(ProcessCase::getProjectId, projectId)
        .in(ProcessCase::getId, processCaseBatchReq.getCaseIds())
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .set(ProcessCase::getStatus, Boolean.FALSE)
        .set(ProcessCase::getRemark, processCaseBatchReq.getRemark())
        .update();
  }

  @Override
  public Boolean batchUpdateDownCase(ProcessCaseBatchReq processCaseBatchReq, Long projectId) {
    return this.lambdaUpdate()
        .eq(ProcessCase::getProjectId, projectId)
        .in(ProcessCase::getId, processCaseBatchReq.getCaseIds())
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .set(ProcessCase::getStatus, Boolean.TRUE)
        .set(ProcessCase::getRemark, processCaseBatchReq.getRemark())
        .update();
  }

  @Override
  public void updateNodeAOrEdge(Long id, String nodes, String edge) {
    this.lambdaUpdate()
        .eq(ProcessCase::getId, id)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .set(ProcessCase::getNodes, nodes)
        .set(ProcessCase::getEdges, edge)
        .update();
  }

  @Override
  public List<ProcessCase> getIsSubProcess(Long projectId) {
    return this.lambdaQuery()
        .eq(ProcessCase::getProjectId, projectId)
        .eq(ProcessCase::getIsSubProcess, Boolean.TRUE)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public Long getProcessCaseCount(Long projectId) {
    return this.lambdaQuery()
        .eq(ProcessCase::getProjectId, projectId)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public Long getProcessCount(Long projectId, Boolean isSubProcess) {
    return this.lambdaQuery()
        .eq(ProcessCase::getProjectId, projectId)
        .eq(ProcessCase::getIsSubProcess, isSubProcess)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ProcessCase> getProcessCaseList(Long projectId, List<Long> ids) {
    return this.lambdaQuery()
        .eq(ProcessCase::getProjectId, projectId)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .in(ProcessCase::getId, ids)
        .list();
  }

  @Override
  public Long getProcessCaseCreateByCount(Long createById) {
    return this.lambdaQuery()
        .eq(ProcessCase::getCreateById, createById)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .count();
  }

  @Override
  public List<ProcessCase> getAllList(Long projectId) {
    return this.lambdaQuery()
        .eq(ProcessCase::getProjectId, projectId)
        .eq(ProcessCase::getIsDelete, Boolean.FALSE)
        .list();
  }
}
