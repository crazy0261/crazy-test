package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DataSourceConfig;
import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.DataSourceConfigRepositoryService;
import com.example.crazytest.services.DataSourceConfigService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.DataSourceUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/8 15:43
 * @DESRIPTION
 */

@Service
public class DataSourceConfigServiceImp implements DataSourceConfigService {

  @Autowired
  DataSourceConfigRepositoryService dataSourceConfigRepositoryService;

  @Override
  public Boolean save(DataSourceConfigReq dataSourceConfigReq) {
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    BeanUtils.copyProperties(dataSourceConfigReq, dataSourceConfig);
    checkDataSourceConfigReq(dataSourceConfigReq);
    return dataSourceConfigRepositoryService.saveOrUpdate(dataSourceConfig);
  }

  @Override
  public IPage<DataSourceConfig> list(String name, Integer current, Integer pageSize) {
    return dataSourceConfigRepositoryService
        .list(BaseContext.getSelectProjectId(), name, current, pageSize);
  }


  @Override
  public boolean testConnection(DataSourceConfigReq dataSourceConfigReq) {
    AssertUtil.assertNotNull(dataSourceConfigReq, ResultEnum.DATA_SOURCE_NOT_EXIST.getMessage());

    DataSource dataSource = DataSourceUtil.createDataSource(dataSourceConfigReq);

    // 测试连接
    try (
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT 1")
    ) {
      return rs.next();
    } catch (Exception e) {
      return Boolean.FALSE;
    }
  }

  @Override
  public void checkDataSourceConfigReq(DataSourceConfigReq dataSourceConfigReq) {
    DataSourceConfig dataSourceConfig = dataSourceConfigRepositoryService
        .getDatabaseConfig(BaseContext.getSelectProjectId(), dataSourceConfigReq.getEnvId(),
            dataSourceConfigReq.getDbName());
    Long count = dataSourceConfigRepositoryService
        .getCountByProjectIdNameCount(BaseContext.getSelectProjectId(),
            dataSourceConfigReq.getName());

    AssertUtil.assertTrue(ObjectUtils.isEmpty(dataSourceConfigReq.getId()) && count > 0,
        ResultEnum.NAME_REPEAT.getMessage());
    AssertUtil.assertTrue(ObjectUtils.isNotEmpty(dataSourceConfigReq.getId()) && ObjectUtils
            .notEqual(dataSourceConfigReq.getName(), dataSourceConfig.getName()),
        ResultEnum.NAME_REPEAT.getMessage());
    AssertUtil.assertTrue(ObjectUtils.isNotEmpty(dataSourceConfigReq.getId()) && ObjectUtils
            .notEqual(dataSourceConfig.getId(), dataSourceConfigReq.getId()) && count > 0,
        ResultEnum.DATA_SOURCE_NOT_EXIST.getMessage());
  }

  @Override
  public boolean del(Long id) {
    return dataSourceConfigRepositoryService.removeById(id);
  }

}
