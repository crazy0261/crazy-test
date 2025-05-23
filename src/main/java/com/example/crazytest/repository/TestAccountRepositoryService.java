package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TestAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 测试账号表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-20
 */
public interface TestAccountRepositoryService extends IService<TestAccount> {

  IPage<TestAccount> list(Long projectId, String name, String genTokenStatus,
      int current, int pageSize);

  List<TestAccount> listAllTestAccount();

  List<TestAccount> getProjectEnvTestAccount(Long projectId);

  TestAccount enable(Long id);

}
