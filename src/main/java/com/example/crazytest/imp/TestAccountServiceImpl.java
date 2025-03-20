package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.repository.TestAccountRepositoryService;
import com.example.crazytest.services.TestAccountService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.TestAccountVO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public IPage<TestAccountVO> list(String name, String account, String envName, int current,
      int pageSize) {

    String tenantId = BaseContext.getTenantId();

    List<EnvConfig> envConfigs = envConfigRepositoryService.getEnvConfigByName(tenantId, name);
    List<String> envConfigIds = envConfigs.stream()
        .map(envConfig -> String.valueOf(envConfig.getEnvNameId())).collect(Collectors.toList());
    IPage<TestAccount> testAccountPage = testAccountRepositoryService
        .list(tenantId, name, account, envConfigIds, current, pageSize);

    return testAccountPage.convert(testAccount -> {
      TestAccountVO testAccountVo = new TestAccountVO();
      BeanUtils.copyProperties(testAccount, testAccountVo);
      EnvConfig envConfig = envConfigRepositoryService.getEnvName(testAccount.getEnvId());
      testAccountVo.setEnvName(envConfig.getName());
      return testAccountVo;
    });
  }
}
