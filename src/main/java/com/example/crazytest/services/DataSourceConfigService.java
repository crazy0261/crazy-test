package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.DataSourceDTO;
import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.example.crazytest.vo.DataSourceConfigVO;

public interface DataSourceConfigService {

  Boolean save(DataSourceConfigReq dataSourceConfigReq);

  IPage<DataSourceConfigVO> list(String name, Integer current, Integer pageSize);

  boolean testConnection(DataSourceConfigReq dataSourceConfigReq);

  void checkDataSourceConfigReq(DataSourceConfigReq dataSourceConfigReq);

  Boolean del(Long id);

  DataSourceDTO getDataSourceDTO(Long id, Long envId);


}
