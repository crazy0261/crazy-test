package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.req.EncryptInfoReq;
import com.example.crazytest.vo.EncryptInfoVO;

public interface EncryptInfoService {

  IPage<EncryptInfoVO> list(String name, Integer current, Integer pageSize);

  Boolean save(EncryptInfoReq encryptInfo);

  JSONObject getEncryptInfoEnv(Long id, Long envId);


}
