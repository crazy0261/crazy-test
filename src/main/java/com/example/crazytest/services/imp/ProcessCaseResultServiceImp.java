package com.example.crazytest.services.imp;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.convert.ProcessCaseNodeResultCovert;
import com.example.crazytest.convert.mapArrayConvert;
import com.example.crazytest.entity.CaseResultCountEntity;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.entity.req.ApiCaseResultReq;
import com.example.crazytest.enums.CaseTypeEnums;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ProcessCaseResultService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ProcessCaseResultDetailVO;
import com.example.crazytest.vo.ProcessCaseResultVO;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/15 14:42
 * @DESRIPTION
 */

@Service
public class ProcessCaseResultServiceImp implements ProcessCaseResultService {

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  ProcessCaseResultRepositoryService repositoryService;

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @Lazy
  @Autowired
  ProcessCaseService processCaseService;

  @Autowired
  UserService userService;

  @Override
  public void save(ProcessCase processCase, ExecutionProcessContext context) {
    ProcessCaseRecord processCaseRecord = ProcessCaseNodeResultCovert
        .processCaseResultConvert(processCase, context);

    repositoryService.saveOrUpdate(processCaseRecord);
  }

  @Override
  public void updateNodes(Long id, Map<String, Node> nodes, String status) {
    repositoryService.updateNodes(id, mapArrayConvert.nodeMapToJsonArray(nodes).toString(), status);
  }

  /**
   * 获取用例执行结果统计
   *
   * @return
   */
  @Override
  public CaseResultCountEntity getCaseResultCount() {
    CaseResultCountEntity countEntity = new CaseResultCountEntity();
    List<ProcessCaseRecord> processCaseRecordList = repositoryService
        .getProcessCaseRecordList(BaseContext.getSelectProjectId());
    List<ProcessCaseRecord> processCaseFilterValidList = processCaseRecordList.stream().filter(
        processCaseRecord -> getProcessCaseFilterValid(processCaseRecordList)
            .contains(processCaseRecord.getCaseId())).distinct()
        .collect(Collectors.toList());

    Map<Long, Optional<ProcessCaseRecord>> latestRecordsByCaseId = processCaseFilterValidList
        .stream()
        .collect(Collectors.groupingBy(ProcessCaseRecord::getCaseId,
            Collectors.collectingAndThen(Collectors.toList(), this::getLatestRecord)));

    long processCaseSuccessCount = apiCaseResultService.countByStatus(latestRecordsByCaseId,
        processCaseRecord -> ExecStatusEnum.SUCCESS.name()
            .equalsIgnoreCase(processCaseRecord.getStatus()));
    long apiCaseProcessFailCount = apiCaseResultService.countByStatus(latestRecordsByCaseId,
        processCaseRecord -> !ExecStatusEnum.SUCCESS.name()
            .equalsIgnoreCase(processCaseRecord.getStatus()));

    countEntity
        .setApiProcessCaseCount(Convert.toInt(processCaseSuccessCount + apiCaseProcessFailCount));
    countEntity.setApiCaseProcessSuccessCount(Convert.toInt(processCaseSuccessCount));
    countEntity.setApiCaseProcessFailCount(Convert.toInt(apiCaseProcessFailCount));
    return countEntity;
  }

  /**
   * 获取有效的用例id
   *
   * @param processCaseRecordList
   * @return
   */
  @Override
  public List<Long> getProcessCaseFilterValid(List<ProcessCaseRecord> processCaseRecordList) {
    List<Long> ids = processCaseRecordList.stream().map(ProcessCaseRecord::getCaseId).distinct()
        .collect(Collectors.toList());
    List<ProcessCase> processCaseList = processCaseRepositoryService
        .getProcessCaseList(BaseContext.getSelectProjectId(), ids);

    return processCaseList.stream().map(ProcessCase::getId)
        .collect(Collectors.toList());
  }

  /**
   * 获取最新一条记录
   *
   * @param records
   * @return
   */
  @Override
  public Optional<ProcessCaseRecord> getLatestRecord(List<ProcessCaseRecord> records) {
    return records.stream()
        .max(Comparator.comparing(ProcessCaseRecord::getUpdateTime));
  }

  @Override
  public ProcessCaseResultVO getProcessCaseResult(String id) {
    ProcessCaseResultVO processCaseResult = new ProcessCaseResultVO();
    ProcessCaseRecord processCaseRecord = repositoryService.getById(Convert.toLong(id));

    ProcessCase processCase = processCaseRepositoryService.getById(processCaseRecord.getCaseId());

    processCaseResult.setId(processCase.getId());
    processCaseResult.setAppId(processCaseRecord.getAppId());
    processCaseResult.setAccountId(processCaseRecord.getAccountId());
    processCaseResult.setCaseId(processCaseRecord.getCaseId());
    processCaseResult.setStatus(processCaseRecord.getStatus());
    processCaseResult.setCaseName(processCase.getName());
    processCaseResult.setEnvSortId(processCaseRecord.getEnvSortId());
    processCaseResult.setNodeArray(JSON.parseArray(processCaseRecord.getNodes()));
    processCaseResult.setEdgeArray(JSON.parseArray(processCaseRecord.getEdges()));
    return processCaseResult;
  }

  @Override
  public IPage<ApiCaseResultReq> getProcessCaseResultLogs(Long caseId, Integer current,
      Integer pageSize) {
    IPage<ProcessCaseRecord> page = repositoryService
        .getProcessCaseRecordLogList(caseId, current, pageSize);
    return page.convert(processCaseRecord -> {
      ApiCaseResultReq apiCaseResultReq = new ApiCaseResultReq();
      apiCaseResultReq.setId(processCaseRecord.getId());
      apiCaseResultReq.setScheduleBatchId(processCaseRecord.getScheduleBatchId());
      apiCaseResultReq.setStatus(processCaseRecord.getStatus());
      apiCaseResultReq.setCreateTime(processCaseRecord.getCreateTime());
      return apiCaseResultReq;
    });
  }

  /**
   * 查询用例执行结果详情
   *
   * @param scheduleBatchId
   * @param current
   * @param pageSize
   * @return
   */
  @Override
  public IPage<ProcessCaseResultDetailVO> getProcessCaseResultDetail(String scheduleBatchId,
      Integer current, Integer pageSize) {
    Long scheduleBatchIdLong = Long.valueOf(scheduleBatchId);
    List<Long> caseIds = lastProcessCaseRecordIds(scheduleBatchIdLong);
    Map<Long, String> userMap = userService.getUserListAllMap();
    Map<Long, String> caseNameMap = processCaseService.getProcessCaseCaseNameMap();
    Map<Long, Long> ownerMap = processCaseService.getProcessCaseOwnerMap();
    IPage<ProcessCaseRecord> page = repositoryService
        .getTaskRecordDetailList(scheduleBatchIdLong, caseIds, current, pageSize);

    return page.convert(processCaseRecord -> {
      ProcessCaseResultDetailVO processCaseResult = new ProcessCaseResultDetailVO();
      BeanUtils.copyProperties(processCaseRecord, processCaseResult);

      List<ProcessCaseResultDetailVO> children = getProcessCaseRecordChildren(
          BaseContext.getSelectProjectId(), scheduleBatchIdLong, processCaseRecord.getCaseId(),
          ownerMap, userMap, processCaseRecord.getId(), caseNameMap);

      processCaseResult.setOwnerName(userMap.get(ownerMap.get(processCaseRecord.getId())));
      processCaseResult.setCaseName(caseNameMap.get(processCaseRecord.getCaseId()));
      processCaseResult.setCaseType(CaseTypeEnums.PROCESS_CASE_TYPE.getDesc());
      processCaseResult.setChildren(children);
      return processCaseResult;
    });
  }

  @Override
  public List<Long> lastProcessCaseRecordIds(Long scheduleBatchId) {
    List<ProcessCaseRecord> processCaseRecords = repositoryService
        .getScheduleBatchIdList(scheduleBatchId);

    Map<Long, ProcessCaseRecord> longApiCaseRecordMap = processCaseRecords.stream().collect(
        Collectors.groupingBy(ProcessCaseRecord::getCaseId, Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparing(ProcessCaseRecord::getUpdateTime)),
            opt -> opt.orElse(null)
        )));
    return longApiCaseRecordMap.values().stream().map(ProcessCaseRecord::getId)
        .collect(Collectors.toList());


  }

  @Override
  public List<ProcessCaseResultDetailVO> getProcessCaseRecordChildren(Long projectId,
      Long scheduleBatchId, Long caseId, Map<Long, Long> ownerMap, Map<Long, String> userMap,
      Long id, Map<Long, String> caseNameMap) {
    List<ProcessCaseRecord> processCaseRecordList = repositoryService
        .getProcessCaseRecordChildren(projectId, scheduleBatchId, caseId, id);

    return processCaseRecordList.stream()
        .map(processCaseRecord -> {
          ProcessCaseResultDetailVO processCaseResultDetailVO = new ProcessCaseResultDetailVO();
          BeanUtils.copyProperties(processCaseRecord, processCaseResultDetailVO);
          processCaseResultDetailVO
              .setOwnerName(userMap.get(ownerMap.get(processCaseRecord.getId())));
          processCaseResultDetailVO.setCaseName(caseNameMap.get(processCaseRecord.getCaseId()));
          return processCaseResultDetailVO;
        }).collect(Collectors.toList());
  }
}
