package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;

public interface DomainInfoService {

  IPage<DomainInfo> list(String name, String urlPath, int current, int pageSize);

  boolean save(DomainInfo domainInfo);
}
