package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.vo.TestAccountVO;
import java.io.IOException;
import java.util.List;

public interface TestAccountService {

  IPage<TestAccountVO> list(String name, String genTokenStatus, int current, int pageSize);

  Boolean save(TestAccount testAccount);

  List<TestAccount> listAllTestAccount();

  Boolean delete(Long id);

  void createToken(TestAccount testAccount) throws IOException;

  void crateManualToken(Long id) throws IOException;

  TestAccount queryById(Long id);

}
