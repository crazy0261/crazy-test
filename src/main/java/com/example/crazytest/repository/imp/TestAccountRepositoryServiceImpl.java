package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.mapper.TestAccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.repository.TestAccountRepositoryService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试账号表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-20
 */
@Service
public class TestAccountRepositoryServiceImpl extends
    ServiceImpl<TestAccountMapper, TestAccount> implements
    TestAccountRepositoryService {

  @Override
  public IPage<TestAccount> list(String tenantId, String name, String account, List<String> envIds,
      int current, int pageSize) {

    return this.lambdaQuery()
        .eq(TestAccount::getTenantId, tenantId)
        .like(ObjectUtils.isNotNull(name), TestAccount::getName, name)
        .like(ObjectUtils.isNotNull(account), TestAccount::getAccount, account)
        .in(ObjectUtils.isNotNull(envIds), TestAccount::getEnvId, envIds)
        .eq(TestAccount::getIsDelete, Boolean.FALSE)
        .orderByDesc(TestAccount::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }
}
