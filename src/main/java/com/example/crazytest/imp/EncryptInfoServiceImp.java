package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.EncryptInfo;
import com.example.crazytest.entity.req.EncryptInfoReq;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.EncryptInfoRepositoryService;
import com.example.crazytest.services.EncryptInfoService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.vo.EncryptInfoVO;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Override
  public IPage<EncryptInfoVO> list(String name, Integer current, Integer pageSize) {
    IPage<EncryptInfo> page = encryptInfoRepository
        .list(BaseContext.getSelectProjectId(), name, current, pageSize);

    return page.convert(encryptInfo -> {
      EncryptInfoVO encryptInfoVO = new EncryptInfoVO();
      BeanUtils.copyProperties(encryptInfo, encryptInfoVO);
      ApplicationManagement appManagement = applicationManagementRepositoryService
          .getById(encryptInfo.getAppId());
      encryptInfoVO.setAppName(appManagement.getName());
      return encryptInfoVO;
    });
  }

  @Override
  public Boolean save(EncryptInfoReq encryptInfoReq) {
    EncryptInfo encryptInfo = new EncryptInfo();
    BeanUtils.copyProperties(encryptInfoReq, encryptInfo);
    encryptInfo.setProjectId(
        Optional.ofNullable(encryptInfo.getProjectId()).orElse(BaseContext.getSelectProjectId()));
    encryptInfo.setEncryptJson(JSON.toJSONString(encryptInfoReq.getEncryptJson()));
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
