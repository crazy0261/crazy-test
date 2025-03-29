package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.TestAccount;
import com.example.crazytest.vo.TestAccountVO;
import java.util.List;

public interface TestAccountService {

  IPage<TestAccountVO> list( String name, String genTokenStatus, int current, int pageSize);

  boolean save(TestAccount testAccount);

  List<TestAccount> listAllTestAccount();

}
