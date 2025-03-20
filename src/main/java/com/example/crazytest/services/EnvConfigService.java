package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.vo.EnvConfigVO;

public interface EnvConfigService {

  IPage<EnvConfigVO> list(String appid, String name, String domainName, int current, int pageSize);

}
