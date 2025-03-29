package com.example.crazytest.job;

import com.alibaba.fastjson.JSONPath;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.TestAccountService;
import com.example.crazytest.vo.ResultApiVO;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/3/29 16:49
 * @DESRIPTION
 */

@Component
@Slf4j
public class TestAccountTokenJob {

  @Autowired
  TestAccountService testAccountService;

  @Autowired
  ApiCaseService apiCaseService;


  @XxlJob("createAccountTokenJob")
  public ReturnT<String> createAccountTokenJob() {

    List<TestAccount> testAccountList = testAccountService.listAllTestAccount();

    CompletableFuture.allOf(testAccountList.stream().map(testAccount ->
        CompletableFuture.supplyAsync(() -> {
          ApiDebugReq apiDebugReq = ApiDebugReq.builder().id(testAccount.getApiCaseId())
              .envId(testAccount.getEnvId()).inputParams(testAccount.getInputParams()).build();
          try {
            ResultApiVO result = apiCaseService.debug(apiDebugReq);
            testAccount
                .setGenTokenStatus(result.getAssertResultVo().isPass() ? "SUCCESS" : "Error");
            testAccount.setToken(Optional.ofNullable(result.getResponse())
                .map(json -> JSONPath.eval(json, testAccount.getJsonPath()))
                .map(Object::toString).orElse(""));
            testAccount.setFailReason(
                !result.getAssertResultVo().isPass() ? result.getResponse().toJSONString() : "");
            testAccountService.save(testAccount);
          } catch (IOException e) {
            log.error("createAccountTokenJob：{}", e.getMessage());
          }
          return null;
        })).toArray(CompletableFuture[]::new)).join();

    return ReturnT.SUCCESS;

  }

}
