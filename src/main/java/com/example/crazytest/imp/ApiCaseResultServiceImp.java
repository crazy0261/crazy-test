package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ExecModeEnum;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.entity.req.ApiCaseResultReq;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.ResultApiVO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 13:32
 * @DESRIPTION
 */

@Service
public class ApiCaseResultServiceImp implements ApiCaseResultService {

  @Autowired
  ApiCaseResultRepositoryService apiCaseResultRepositoryService;


  @Autowired
  ApiCaseRepositoryService apiCaseRepository;

  @Override
  public ApiCaseRecord queryById(Long id) {
    return apiCaseResultRepositoryService.getById(id);
  }

  @Override
  public IPage<ApiCaseResultReq> list(String apiTestcaseId, Integer current, Integer pageSize) {
    IPage<ApiCaseRecord> apiCaseResult = apiCaseResultRepositoryService
        .list(apiTestcaseId, current, pageSize);

    return apiCaseResult.convert(caseResult -> {
      ApiCaseResultReq apiCaseResultVo = new ApiCaseResultReq();
      BeanUtils.copyProperties(caseResult, apiCaseResultVo);
      return apiCaseResultVo;
    });
  }

  @Override
  public boolean save(ApiDebugReq apiDebugReq, ResultApiVO resultApiVO) {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());

    ApiCaseRecord apiCaseRecord = ApiCaseRecord.builder()
        .tenantId(BaseContext.getTenantId())
        .apiTestcaseId(apiDebugReq.getId())
        .caseOwnerId(apiCase.getOwnerId())
        .mode(Objects.isNull(apiDebugReq.getMode()) ? ExecModeEnum.MANUAL.getValue()
            : ExecModeEnum.AUTO.getValue())
        .status(Optional.ofNullable(resultApiVO.getAssertResultVo()).map(
            AssertResultVo::getPass).filter(pass -> pass)
            .map(pass -> ExecStatusEnum.SUCCESS.getValue()).orElse(ExecStatusEnum.FAIL.getValue()))
        .debugResult(JSON.toJSONString(resultApiVO))
        .scheduleId(apiDebugReq.getScheduleId())
        .scheduleBatchId(apiDebugReq.getScheduleBatchId())
        .build();
    return apiCaseResultRepositoryService.save(apiCaseRecord);
  }

  @Override
  public List<Long> listResult(String tenantId, String recentExecResult) {
    List<ApiCaseRecord> apiCaseRecords = apiCaseResultRepositoryService
        .listResult(tenantId, recentExecResult);
    return apiCaseRecords.stream().map(ApiCaseRecord::getApiTestcaseId)
        .collect(Collectors.toList());
  }
}
