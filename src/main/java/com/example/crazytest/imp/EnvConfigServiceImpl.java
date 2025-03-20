package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.EnvConfigVo;
import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.example.crazytest.services.EnvConfigService;
import com.example.crazytest.utils.BaseContext;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 20:47
 * @DESRIPTION
 */

@Service
public class EnvConfigServiceImpl implements EnvConfigService {

  @Autowired
  EnvConfigRepositoryService envConfigRepositoryService;

  @Override
  public IPage<EnvConfigVo> list(String appid, String name, String domainName, int current,
      int pageSize) {

    // todo 域名逻辑代补充 根据域名名称查询id

    IPage<EnvConfig> envConfigIPage = envConfigRepositoryService
        .list(BaseContext.getTenantId(), appid, name, new ArrayList<>(), current, pageSize);
    return envConfigIPage.convert(envConfig -> {
      EnvConfigVo envConfigVo = new EnvConfigVo();
      BeanUtils.copyProperties(envConfig, envConfigVo);

      // todo   根据域名id查询名称
      return envConfigVo;
    });
  }
}
