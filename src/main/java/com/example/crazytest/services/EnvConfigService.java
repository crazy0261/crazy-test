package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.entity.req.EnvConfigReq;
import com.example.crazytest.vo.EnvConfigVO;
import java.util.List;

public interface EnvConfigService {

  IPage<EnvConfigVO> list(String appid, String name, String sort, String domainName, int current,
      int pageSize);

  EnvConfig getByAppId(Long appId,Long envId);

  Boolean save(EnvConfigReq envConfigReq);

  EnvConfigReq queryById(Long id);

  List<EnvConfig> listAll();

  Boolean delete(Long id);

  List<EnvConfig> envAppList(Long appId);
}
