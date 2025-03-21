package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.repository.DomainInfoRepositoryService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @name Menghui
 * @date 2025/3/21 21:33
 * @DESRIPTION
 */

@Service
public class DomainInfoServiceImpl implements DomainInfoService {

  @Autowired
  DomainInfoRepositoryService domainInfoRepositoryService;


  @Override
  public IPage<DomainInfo> list(String name, String urlPath, int current, int pageSize) {

    return domainInfoRepositoryService
        .list(BaseContext.getTenantId(), name, urlPath, current, pageSize);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean save(DomainInfo domainInfo) {
    domainInfo.setTenantId(BaseContext.getTenantId());
    return domainInfoRepositoryService.saveOrUpdate(domainInfo);
  }

}
