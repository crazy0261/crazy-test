package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EncryptInfo;
import com.example.crazytest.entity.req.EncryptInfoReq;
import com.example.crazytest.vo.EncryptInfoVO;
import java.util.List;

public interface EncryptInfoService {

  IPage<EncryptInfoVO> list(String name, Integer current, Integer pageSize);

  Boolean save(EncryptInfoReq encryptInfo);

  Boolean del(Long id);

  JSONObject getEncryptInfoEnv(Long id, Long envId);

  List<EncryptInfo> getAppIds(Long appId);


}
