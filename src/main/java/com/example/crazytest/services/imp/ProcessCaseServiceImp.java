package com.example.crazytest.services.imp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.example.crazytest.entity.ProcessCaseRecord;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.ProcessCaseBatchReq;
import com.example.crazytest.entity.req.ProcessCaseReq;
import com.example.crazytest.enums.NodeTypeEnum;
import com.example.crazytest.enums.PriorityEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.ProcessCaseResultRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.FlowEngineService;
import com.example.crazytest.services.ProcessCaseService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.CommonUtil;
import com.example.crazytest.vo.ProcessCaseVO;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/6 13:40
 * @DESRIPTION
 */

@Slf4j
@Service
public class ProcessCaseServiceImp implements ProcessCaseService {

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  ProcessCaseResultRepositoryService processCaseResultRepositoryService;

  @Autowired
  UserRepositoryService userRepository;

  @Autowired
  FlowEngineService flowEngineService;

  /**
   * 分页查询
   *
   * @param processCaseDTO
   * @return
   */
  @Override
  public IPage<ProcessCaseVO> listPage(ProcessCaseDTO processCaseDTO) {

    List<Long> ids = Optional.ofNullable(processCaseDTO.getRecentExecResult())
        .map(result -> processCaseResultRepositoryService
            .list(BaseContext.getSelectProjectId(), result).stream()
            .map(ProcessCaseRecord::getCaseId).collect(Collectors.toList()))
        .orElse(null);

    if (StringUtils.isNotBlank(processCaseDTO.getRecentExecResult()) && CollUtil.isEmpty(ids)) {
      return new Page<>();
    }

    IPage<ProcessCase> listPage = processCaseRepositoryService
        .listPage(processCaseDTO, BaseContext.getSelectProjectId(), ids);

    return listPage.convert(processCase -> {
      ProcessCaseRecord processCaseRecord = processCaseResultRepositoryService
          .lastResult(BaseContext.getSelectProjectId(), processCase.getId());

      ProcessCaseVO processCaseVO = new ProcessCaseVO();
      BeanUtils.copyProperties(processCase, processCaseVO);

      processCaseVO.setPriorityDesc(PriorityEnum.getDescByCode(processCase.getPriority()));
      processCaseVO.setOwnerName(userRepository.getUserData(processCase.getOwnerId()).getName());
      Optional.ofNullable(processCaseRecord).ifPresent(result -> {
        processCaseVO
            .setRecentExecResult(Optional.ofNullable(processCaseRecord.getStatus()).orElse(""));
        processCaseVO
            .setRecentExecTime(Optional.ofNullable(processCaseRecord.getCreateTime()).orElse(null));
      });
      return processCaseVO;
    });
  }

  /**
   * 检验是否有开始节点
   *
   * @param nodes
   */
  @Override
  public void checkNodeStartType(List<JSONObject> nodes) {
    Boolean isStartNode = nodes.stream()
        .anyMatch(node -> node.getString("type").equals(NodeTypeEnum.START_NODE.getTypeName()));
    AssertUtil.assertNotTrue(isStartNode, ResultEnum.START_NODE_NOT_EXIST.getMessage());
  }

  @Override
  public Boolean save(ProcessCaseReq processCaseReq) {
    if (CollUtil.isNotEmpty(processCaseReq.getNodes())) {
      checkNodeStartType(processCaseReq.getNodes());
    }

    ProcessCase processCase = new ProcessCase();
    BeanUtils.copyProperties(processCaseReq, processCase);
    processCase.setInputParams(
        Optional.ofNullable(processCaseReq.getInputParams()).map(JSONObject::toString)
            .orElse("{}"));
    processCase.setProjectId(
        Optional.ofNullable(processCase.getProjectId()).orElse(BaseContext.getSelectProjectId()));
    processCase
        .setOwnerId(Optional.ofNullable(processCase.getOwnerId()).orElse(BaseContext.getUserId()));
    processCase.setNodes(
        Optional.ofNullable(processCaseReq.getNodes()).map(JSONArray::toJSONString).orElse("[]"));
    processCase.setEdges(
        Optional.ofNullable(processCaseReq.getEdges()).map(JSONArray::toJSONString).orElse("[]"));
    return processCaseRepositoryService.saveOrUpdate(processCase);
  }

  @Override
  public Boolean batchUpdateOwner(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateOwner(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateMove(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateMove(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateUpCase(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateUpCase(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean batchUpdateDownCase(ProcessCaseBatchReq processCaseBatchReq) {
    return processCaseRepositoryService
        .batchUpdateDownCase(processCaseBatchReq, BaseContext.getSelectProjectId());
  }

  @Override
  public Boolean copy(ProcessCase processCase) {
    ProcessCase processCaseData = processCaseRepositoryService.getById(processCase.getId());
    processCaseData.setName(processCaseData.getName() + "-copy");
    processCaseData.setId(null);
    return processCaseRepositoryService.save(processCaseData);
  }

  @Override
  public Boolean delete(ProcessCase processCase) {
    return processCaseRepositoryService.removeById(processCase.getId());
  }

  @Override
  public ProcessCaseVO detail(Long id) {
    ProcessCaseVO processCaseVO = new ProcessCaseVO();
    ProcessCase processCase = processCaseRepositoryService.getById(id);
    BeanUtils.copyProperties(processCase, processCaseVO);
    processCaseVO.setNodeArray(JSON.parseArray(processCase.getNodes(), JSONObject.class));
    processCaseVO.setEdgesArray(JSON.parseArray(processCase.getEdges(), JSONObject.class));
    processCaseVO.setInputParamsJson(JSON.parseObject(processCase.getInputParams()));
    return processCaseVO;
  }

  /**
   * 更新节点或边
   *
   * @param id
   * @param nodes
   * @param edge
   */
  @Override
  public void updateNodeAOrEdge(Long id, List<JSONObject> nodes, List<JSONObject> edge) {
    processCaseRepositoryService
        .updateNodeAOrEdge(id, JSON.toJSONString(nodes), JSON.toJSONString(edge));
  }

  @Override
  public List<ProcessCase> getIsSubProcess() {
    return processCaseRepositoryService.getIsSubProcess(BaseContext.getSelectProjectId());
  }

  /**
   * 执行流程
   *
   * @param apiDebugReq
   * @return
   */
  @Override
  public String debug(ApiDebugReq apiDebugReq) {
    ExecutionProcessContext context = new ExecutionProcessContext();
    ProcessCase processCase = processCaseRepositoryService.getById(apiDebugReq.getId());
    AssertUtil.assertNotNull(processCase, ResultEnum.PROCESS_CASE_NOT_FAIL.getMessage());
    AssertUtil.assertNotNull(processCase.getAppId(),
        ResultEnum.PROCESS_CASE_NODE_NOT_SELECT_APP.getMessage());

    apiDebugReq.setEnvId(
        Objects.nonNull(apiDebugReq.getEnvSortId()) ? Convert.toInt(apiDebugReq.getEnvSortId())
            : apiDebugReq.getEnvId());
    context.setId(apiDebugReq.getId());
    context.setApiDebugReq(apiDebugReq);
    context.setProjectId(processCase.getProjectId());
    context.setScheduleId(IdUtil.getSnowflakeNextId());
    context.setScheduleBatchId(apiDebugReq.getScheduleBatchId());
    context.setResultId(IdUtil.getSnowflakeNextId());
    context.setProcessCase(processCase);

    flowEngineService.executeFlow(processCase, context);

    return pollingQuery(context.getResultId());
  }

  /**
   * 执行子流程
   *
   * @param envParameter
   * @param subCaseId
   * @param subEnvId
   * @return
   */
  @Override
  public Long executeSubTask(Map<String, String> envParameter, Long subCaseId, Long subEnvId) {
    ApiDebugReq apiSubDebugReq = new ApiDebugReq();
    apiSubDebugReq.setId(subCaseId);
    apiSubDebugReq.setEnvSubParameter(envParameter);
    apiSubDebugReq.setEnvId(subEnvId);
    return Convert.toLong(debug(apiSubDebugReq));
  }

  /**
   * 轮询查询
   * @param resultId
   * @return
   */
  @Override
  public String pollingQuery(Long resultId) {
    long startTime = System.currentTimeMillis();

    while (System.currentTimeMillis() - startTime < CommonUtil.MAX_WAIT_TIME_MILLIS) {
      ProcessCaseRecord processCaseRecord = processCaseResultRepositoryService.getById(resultId);

      if (Objects.nonNull(processCaseRecord)) {
        return resultId.toString();
      }
      try {
        TimeUnit.MILLISECONDS.sleep(CommonUtil.POLLING_INTERVAL_MILLIS);
      } catch (InterruptedException e) {
        // 中断线程
        Thread.currentThread().interrupt();

        log.error("pollingQuery error", e);
        break;
      }
    }

    return "";
  }

  /**
   * 获取流程用例的所有者id
   * @return
   */
  @Override
  public Map<Long, Long> getProcessCaseOwnerMap() {
    List<ProcessCase> processCaseList =processCaseRepositoryService.getAllList(BaseContext.getSelectProjectId());
    return processCaseList.stream().collect(Collectors.toMap(ProcessCase::getId, ProcessCase::getOwnerId));
  }

  /**
   * 获取流程用例的名称
   * @return
   */
  @Override
  public Map<Long, String> getProcessCaseCaseNameMap() {
    List<ProcessCase> processCaseList =processCaseRepositoryService.getAllList(BaseContext.getSelectProjectId());
    return processCaseList.stream().collect(Collectors.toMap(ProcessCase::getId, ProcessCase::getName));
  }
}
