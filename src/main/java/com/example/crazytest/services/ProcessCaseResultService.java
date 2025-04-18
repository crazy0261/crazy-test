package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.CaseResultCountEntity;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseRecord;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProcessCaseResultService {

  Long insert(ProcessCase processCase, ExecutionProcessContext context);

  void updateNodes(Long id, Map<String, Node> nodes, String status);

  CaseResultCountEntity getCaseResultCount();

  List<Long> getProcessCaseFilterValid(List<ProcessCaseRecord> processCaseRecordList);

  Optional<ProcessCaseRecord> getLatestRecord(List<ProcessCaseRecord> records);


}
