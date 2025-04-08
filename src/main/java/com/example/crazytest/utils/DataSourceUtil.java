package com.example.crazytest.utils;

import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/8 22:04
 * @DESRIPTION
 */

@Service
public class DataSourceUtil {

  public static  DataSource createDataSource(DataSourceConfigReq dataSourceConfigReq) {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(dataSourceConfigReq.getUrl());
    hikariConfig.setUsername(dataSourceConfigReq.getUsername());
    hikariConfig.setPassword(dataSourceConfigReq.getPassword());
    hikariConfig.setDriverClassName(dataSourceConfigReq.getDriverClassName());
    hikariConfig.setMinimumIdle(dataSourceConfigReq.getMinPoolSize());
    hikariConfig.setMaximumPoolSize(dataSourceConfigReq.getMaxPoolSize());
    hikariConfig.setIdleTimeout(dataSourceConfigReq.getMaxIdleTime());
    hikariConfig.setMaxLifetime(dataSourceConfigReq.getIdleConnectionTestPeriod());
    hikariConfig.setConnectionTestQuery("SELECT 1");
    return new HikariDataSource(hikariConfig);

  }

}
