package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EncryptInfo;

public interface EncryptInfoService {

  IPage<EncryptInfo> list(String name, Integer current, Integer pageSize);

  Boolean save(EncryptInfo encryptInfo);

  JSONObject getEncryptInfoEnv(Long id, Long envId);


}
