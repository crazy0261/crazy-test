package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import java.util.List;

public interface DomainInfoService {

  IPage<DomainInfo> list(String name, String urlPath, int current, int pageSize);

  Boolean save(DomainInfo domainInfo);

  DomainInfo getById(Long id);

  List<DomainInfo> getByNameList(String name);

  Boolean delete(Long id);

}
