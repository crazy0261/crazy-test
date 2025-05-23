package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import com.example.crazytest.entity.req.ProcessCaseReq;
import com.example.crazytest.vo.ProcessCaseVO;
import java.util.List;
import java.util.Map;

public interface ProcessCaseService {

  IPage<ProcessCaseVO> listPage(ProcessCaseDTO processCaseDTO);

  void checkNodeStartType(List<JSONObject> nodes);

  Boolean save(ProcessCaseReq processCaseReq);

  Boolean batchUpdateOwner(ProcessCaseBatchReq processCaseBatchReq);

  Boolean batchUpdateMove(ProcessCaseBatchReq processCaseBatchReq);

  Boolean batchUpdateUpCase(ProcessCaseBatchReq processCaseBatchReq);

  Boolean batchUpdateDownCase(ProcessCaseBatchReq processCaseBatchReq);

  Boolean copy(ProcessCase processCase);

  Boolean delete(ProcessCase processCase);

  ProcessCaseVO detail(Long id);

  void updateNodeAOrEdge(Long id, List<JSONObject> nodes, List<JSONObject> edge);

  List<ProcessCase> getIsSubProcess();

  String  debug(ApiDebugReq apiDebugReq);

  Long executeSubTask(Map<String, String> envParameter, Long subCaseId, Long subEnvId);

  String pollingQuery(Long resultId);

  Map<Long,Long > getProcessCaseOwnerMap();

  Map<Long,String > getProcessCaseCaseNameMap();



}
