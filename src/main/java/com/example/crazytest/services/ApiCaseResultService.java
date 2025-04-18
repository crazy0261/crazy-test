package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.entity.CaseResultCountEntity;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.ApiCaseResultReq;
import com.example.crazytest.vo.ApiCaseResultVO;
import com.example.crazytest.vo.ResultApiVO;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public interface ApiCaseResultService {

  ApiCaseRecord queryById(Long id);

  IPage<ApiCaseResultReq> list(String apiTestcaseId, Integer current, Integer pageSize);

  boolean save(ApiDebugReq apiDebugReq, ResultApiVO resultApiVO);

  List<Long> listResult(Long projectId, String recentExecResult);

  List<Long> lastExecResult(Long scheduleBatchId);

  IPage<ApiCaseRecord> resultList(List<Long> apiCaseResultIds, Integer current, Integer pageSize);

  IPage<ApiCaseResultVO> getApiResultDetail(Long scheduleBatchId, Integer current, Integer pageSize);

  List<ApiCaseResultVO> getApiCaseRecordChildrenCovert(List<ApiCaseRecord> apiCaseRecordList, String ownerName, String caseName);

  CaseResultCountEntity getApiCaseSuccessCount();

  List<Long> getFilterValid(List<ApiCaseRecord> apiCaseRecordList);

  Optional<ApiCaseRecord> getLatestRecord(List<ApiCaseRecord> records);

  <T> long countByStatus(Map<Long, Optional<T>> recordsByCaseId, Predicate<T> filterCondition);
}
