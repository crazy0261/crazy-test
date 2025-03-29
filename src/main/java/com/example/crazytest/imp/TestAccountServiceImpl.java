package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.repository.TestAccountRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.TestAccountService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.CronUtil;
import com.example.crazytest.vo.ApiCaseVO;
import com.example.crazytest.vo.TestAccountVO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 22:22
 * @DESRIPTION
 */

@Service
public class TestAccountServiceImpl implements TestAccountService {

  @Autowired
  TestAccountRepositoryService testAccountRepositoryService;

  @Autowired
  EnvConfigRepositoryService envConfigRepositoryService;

  @Autowired
  ApiCaseService apiCaseService;

  @Override
  public IPage<TestAccountVO> list(String name, String genTokenStatus, int current,
      int pageSize) {

    String tenantId = BaseContext.getTenantId();
    IPage<TestAccount> testAccountPage = testAccountRepositoryService
        .list(tenantId, name, genTokenStatus, current, pageSize);

    return testAccountPage.convert(testAccount -> {
      TestAccountVO testAccountVo = new TestAccountVO();
      BeanUtils.copyProperties(testAccount, testAccountVo);
      EnvConfig envConfig = envConfigRepositoryService.getEnvName(testAccount.getEnvId());
      ApiCaseVO caseVO = apiCaseService.getById(testAccount.getApiCaseId());
      testAccountVo.setApiCaseName(caseVO.getName());
      testAccountVo.setEnvName(envConfig.getName());
      return testAccountVo;
    });
  }

  @Override
  public boolean save(TestAccount testAccount) {
    CronUtil.cronCheckRule(testAccount.getCron());
    CronExpression cron = CronExpression.parse(testAccount.getCron());
    testAccount.setNextExecTime(cron.next(LocalDateTime.now()));
    return testAccountRepositoryService.saveOrUpdate(testAccount);
  }

  @Override
  public List<TestAccount> listAllTestAccount() {
    return testAccountRepositoryService.listAllTestAccount();
  }
}

