package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.vo.TestAccountVO;

public interface TestAccountService {

  IPage<TestAccountVO> list( String name, String account, String genTokenStatus, int current, int pageSize);

}
