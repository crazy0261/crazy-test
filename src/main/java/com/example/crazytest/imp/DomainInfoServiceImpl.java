package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.repository.DomainInfoRepositoryService;
import com.example.crazytest.services.DomainInfoService;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
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
        .list(BaseContext.getSelectProjectId(), name, urlPath, current, pageSize);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean save(DomainInfo domainInfo) {
    domainInfo.setProjectId(BaseContext.getSelectProjectId());
    return domainInfoRepositoryService.saveOrUpdate(domainInfo);
  }

  @Override
  public DomainInfo getById(Long id) {
    return domainInfoRepositoryService.getById(id);
  }

  @Override
  public List<DomainInfo> getByNameList(String name) {
    return domainInfoRepositoryService.getByNameList(BaseContext.getSelectProjectId(), name);
  }

  @Override
  public Boolean delete(Long id) {
    return domainInfoRepositoryService.removeById(id);
  }

}
