package com.example.crazytest.services.imp;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.TestAccountRepositoryService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.vo.ParamsListVO;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:25
 * @DESRIPTION
 */

@Service
public class StartNodeServiceImp implements NodeService {

  @Autowired
  TestAccountRepositoryService repositoryService;

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) {
    ExecutionResult result = new ExecutionResult();
    ApiDebugReq apiDebugReq = context.getApiDebugReq();

    TestAccount testAccount = repositoryService.enable(apiDebugReq.getTestAccount());

    if (Objects.isNull(testAccount)) {
      result.setMessage(ResultEnum.USER_NOT_FOUND.getMessage());
      result.setStatus(ExecStatusEnum.FAILED);
    } else {
      Map<String, String> params = Optional.ofNullable(apiDebugReq.getInputParams()).map(
          item -> item.stream()
              .collect(Collectors.toMap(ParamsListVO::getKey, ParamsListVO::getValue)))
          .orElse(new HashMap<>());

      params.put("token", testAccount.getToken());
      context.setEnvParameter(params);
      result.setStatus(ExecStatusEnum.SUCCESS);
    }
    return result;
  }
}
