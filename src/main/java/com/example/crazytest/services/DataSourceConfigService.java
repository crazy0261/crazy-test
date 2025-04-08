package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DataSourceConfig;
import com.example.crazytest.entity.req.DataSourceConfigReq;

public interface DataSourceConfigService {

  Boolean save(DataSourceConfigReq dataSourceConfigReq);

  IPage<DataSourceConfig> list(String name, Integer current, Integer pageSize);

  boolean testConnection(DataSourceConfigReq dataSourceConfigReq);

  void checkDataSourceConfigReq(DataSourceConfigReq dataSourceConfigReq);

  boolean del(Long id);


}
