package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 场景用例执行结果 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
public interface ProcessCaseResultRepositoryService extends IService<ProcessCaseRecord> {

  List<ProcessCaseRecord> list(Long projectId, String status);

  ProcessCaseRecord lastResult(Long projectId, Long caseId);

  void updateNodes(Long id, String nodes, String status);

  List<ProcessCaseRecord> getProcessCaseRecordList(Long processId);

  List<ProcessCaseRecord> getProcessCaseRecordFailList(Long processId, LocalDateTime time);

  IPage<ProcessCaseRecord> getProcessCaseRecordLogList(Long caseId, Integer current,
      Integer pageSize);

  List<ProcessCaseRecord> getScheduleBatchIdList(Long scheduleBatchId);

  IPage<ProcessCaseRecord> getTaskRecordDetailList(Long scheduleBatchId, List<Long> caseIds,
      Integer current, Integer pageSize);

  List<ProcessCaseRecord> getProcessCaseRecordChildren(Long projectId,Long scheduleBatchId,Long caseId,Long id);

  List<ProcessCaseRecord> getCountStatusByCaseIds(List<Long> caseIds,Long scheduleBatchId);
}
