package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.CaseResultCountEntity;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.entity.TaskSchedule;
import com.example.crazytest.entity.TaskScheduleRecord;
import com.example.crazytest.vo.ProcessCaseResultDetailVO;
import com.example.crazytest.vo.ProcessCaseResultLogVO;
import com.example.crazytest.vo.ProcessCaseResultVO;
import com.example.crazytest.vo.TaskBatchConvergeVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProcessCaseResultService {

  void save(ProcessCase processCase, ExecutionProcessContext context);

  void updateNodes(Long id, Map<String, Node> nodes, String status);

  CaseResultCountEntity getCaseResultCount();

  List<Long> getProcessCaseFilterValid(List<ProcessCaseRecord> processCaseRecordList);

  Optional<ProcessCaseRecord> getLatestRecord(List<ProcessCaseRecord> records);

  ProcessCaseResultVO getProcessCaseResult(String id);

  IPage<ProcessCaseResultLogVO> getProcessCaseResultLogs(Long caseId, Integer current, Integer pageSize);

  IPage<ProcessCaseResultDetailVO> getProcessCaseResultDetail(String scheduleBatchId,
      Integer current, Integer pageSize);

  List<Long> lastProcessCaseRecordIds(Long scheduleBatchId);

  List<ProcessCaseResultDetailVO> getProcessCaseRecordChildren(Long projectId, Long scheduleBatchId,
      Long caseId, Map<Long, Long> ownerMap, Map<Long, String> userMap, Long id,
      Map<Long, String> caseNameMap);

  TaskBatchConvergeVO taskBatchConverge(TaskSchedule taskSchedule, TaskScheduleRecord taskScheduleRecords)
      throws JsonProcessingException;

  Map<String,Long> countStatusByCaseIds(List<Long> caseIds,Long scheduleBatchId);


}
