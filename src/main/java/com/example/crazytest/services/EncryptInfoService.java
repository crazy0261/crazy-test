package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EncryptInfo;
import com.example.crazytest.entity.req.EncryptInfoReq;

public interface EncryptInfoService {

  IPage<EncryptInfo> list(String name, Integer current, Integer pageSize);

  Boolean save(EncryptInfoReq encryptInfo);

  JSONObject getEncryptInfoEnv(Long id, Long envId);


}
