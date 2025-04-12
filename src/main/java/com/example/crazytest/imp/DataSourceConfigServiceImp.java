package com.example.crazytest.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.DataSourceDTO;
import com.example.crazytest.entity.ApplicationManagement;
import com.example.crazytest.entity.DataSourceConfig;
import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.DataSourceConfigRepositoryService;
import com.example.crazytest.services.DataSourceConfigService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.DataSourceUtil;
import com.example.crazytest.vo.DataSourceConfigVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
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

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Override
  public Boolean save(DataSourceConfigReq dataSourceConfigReq) {
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    BeanUtils.copyProperties(dataSourceConfigReq, dataSourceConfig);
    checkDataSourceConfigReq(dataSourceConfigReq);
    dataSourceConfig.setDataSourceJson(JSON.toJSONString(dataSourceConfigReq.getDataSourceJson()));
    dataSourceConfig.setProjectId(BaseContext.getSelectProjectId());
    return dataSourceConfigRepositoryService.saveOrUpdate(dataSourceConfig);
  }

  @Override
  public IPage<DataSourceConfigVO> list(String name, Integer current, Integer pageSize) {
    IPage<DataSourceConfig> page = dataSourceConfigRepositoryService
        .list(BaseContext.getSelectProjectId(), name, current, pageSize);

    return page.convert(dataSourceConfig -> {
      DataSourceConfigVO dataSourceConfigVO = new DataSourceConfigVO();
      ApplicationManagement appManagement = applicationManagementRepositoryService
          .getById(dataSourceConfig.getAppId());
      BeanUtils.copyProperties(dataSourceConfig, dataSourceConfigVO);
      dataSourceConfigVO.setAppName(appManagement.getName());
      return dataSourceConfigVO;
    });
  }

  @Override
  public boolean testConnection(DataSourceConfigReq dataSourceConfigReq) {
    AssertUtil.assertNotNull(dataSourceConfigReq, ResultEnum.DATA_SOURCE_NOT_EXIST.getMessage());

    DataSource dataSource = DataSourceUtil.createDataSource(dataSourceConfigReq);

    // 测试连接
    try {
      assert dataSource != null;
      try (Connection connection = dataSource.getConnection();
          Statement stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT 1")
      ) {
        return rs.next();
      }
    } catch (Exception e) {
      return Boolean.FALSE;
    }
  }

  @Override
  public void checkDataSourceConfigReq(DataSourceConfigReq dataSourceConfigReq) {
    DataSourceConfig dataSourceConfig = dataSourceConfigRepositoryService
        .getDatabaseConfig(BaseContext.getSelectProjectId(),dataSourceConfigReq.getAppId());
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
  public Boolean del(Long id) {
    return dataSourceConfigRepositoryService.removeById(id);
  }

  @Override
  public DataSourceDTO getDataSourceDTO(Long id, Long envId) {
    return Optional.ofNullable(dataSourceConfigRepositoryService.getById(id))
        .map(DataSourceConfig::getDataSourceJson)
        .map(JSONArray::parseArray).flatMap(jsonArray -> jsonArray.stream()
            .map(json -> JSON.parseObject(json.toString()))
            .filter(json -> json.containsKey(envId.toString()))
            .map(json -> json.getJSONObject(envId.toString()))
            .findFirst()
            .map(json -> JSON.toJavaObject(json, DataSourceDTO.class))).orElse(null);
  }

}
