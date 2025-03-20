package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.EnvConfigVo;

public interface EnvConfigService {

  IPage<EnvConfigVo> list(String appid, String name, String domainName, int current, int pageSize);

}
