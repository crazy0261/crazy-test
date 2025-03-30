package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ExecModeEnum;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.ApiCaseResultVo;
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
  public ApiCaseResult queryById(Long id) {
    return apiCaseResultRepositoryService.getById(id);
  }

  @Override
  public IPage<ApiCaseResultVo> list(String apiTestcaseId, Integer current, Integer pageSize) {
    IPage<ApiCaseResult> apiCaseResult = apiCaseResultRepositoryService
        .list(apiTestcaseId, current, pageSize);

    return apiCaseResult.convert(caseResult -> {
      ApiCaseResultVo apiCaseResultVo = new ApiCaseResultVo();
      BeanUtils.copyProperties(caseResult, apiCaseResultVo);
      return apiCaseResultVo;
    });
  }

  @Override
  public boolean save(ApiDebugReq apiDebugReq, ResultApiVO resultApiVO) {
    ApiCase apiCase = apiCaseRepository.getById(apiDebugReq.getId());

    ApiCaseResult apiCaseResult = ApiCaseResult.builder()
        .tenantId(BaseContext.getTenantId())
        .apiTestcaseId(apiDebugReq.getId())
        .caseOwnerId(apiCase.getOwnerId())
        .mode(Objects.isNull(apiDebugReq.getMode()) ? ExecModeEnum.MANUAL.getValue()
            : ExecModeEnum.SCHEDULE.getValue())
        .status(resultApiVO.getAssertResultVo().isPass() ? ExecStatusEnum.SUCCESS.getValue()
            : ExecStatusEnum.FAIL.getValue())
        .debugResult(JSON.toJSONString(resultApiVO))
        .build();
    return apiCaseResultRepositoryService.save(apiCaseResult);
  }

  @Override
  public List<Long> listResult(String tenantId, String recentExecResult) {
    List<ApiCaseResult> apiCaseResults = apiCaseResultRepositoryService
        .listResult(tenantId, recentExecResult);
    return apiCaseResults.stream().map(ApiCaseResult::getApiTestcaseId).collect(Collectors.toList());
  }
}
