package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EncryptInfo;
import com.example.crazytest.repository.EncryptInfoRepositoryService;
import com.example.crazytest.services.EncryptInfoService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/9 16:10
 * @DESRIPTION
 */

@Service
public class EncryptInfoServiceImp implements EncryptInfoService {

  @Autowired
  EncryptInfoRepositoryService encryptInfoRepository;

  @Override
  public IPage<EncryptInfo> list(String name, Integer current, Integer pageSize) {
    return encryptInfoRepository.list(BaseContext.getSelectProjectId(), name, current, pageSize);
  }

  @Override
  public Boolean save(EncryptInfo encryptInfo) {
    return encryptInfoRepository.saveOrUpdate(encryptInfo);
  }

  @Override
  public JSONObject getEncryptInfoEnv(Long id, Long envId) {
    EncryptInfo encryptInfo = encryptInfoRepository
        .getEncryptInfoEnv(BaseContext.getSelectProjectId(), id);
    JSONObject encryptJson = JSON.parseObject(encryptInfo.getEncryptJson());
    return encryptJson.getJSONObject(String.valueOf(envId));
  }

}
