package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import java.util.List;

/**
 * <p>
 * 场景用例 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
public interface ProcessCaseRepositoryService extends IService<ProcessCase> {

  IPage<ProcessCase> listPage(ProcessCaseDTO processCaseDTO, Long projectId, List<Long> ids);

  Boolean batchUpdateOwner(ProcessCaseBatchReq processCaseBatchReq, Long projectId);

  Boolean batchUpdateMove(ProcessCaseBatchReq processCaseBatchReq, Long projectId);

  Boolean batchUpdateUpCase(ProcessCaseBatchReq processCaseBatchReq, Long projectId);

  Boolean batchUpdateDownCase(ProcessCaseBatchReq processCaseBatchReq, Long projectId);

  void updateNodeAOrEdge(Long id, String nodes, String edge);

  List<ProcessCase> getIsSubProcess(Long projectId);

  Long getProcessCaseCount(Long projectId);

  Long getProcessCount(Long projectId,Boolean isSubProcess);

  List<ProcessCase> getProcessCaseList(Long projectId,List<Long> ids);

  Long getProcessCaseCreateByCount(Long createById);

}
