package com.example.crazytest.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @author
 * @name Menghui
 * @date 2025/4/9 00:02
 * @DESRIPTION
 */


public class DatabaseHelper {

  private DataSource dataSource; // 数据库连接池

  public DatabaseHelper(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  // 连接
//  DataSource dataSource = DataSourceUtil.createDataSource(dataSourceConfigReq);


  /**
   * 执行查询，返回多条数据
   *
   * @param sql 查询 SQL
   * @return List<Map<String, Object>> 查询结果，每行数据是一个 Map
   */
  public List<Map<String, Object>> queryForList(String sql) {
    List<Map<String, Object>> resultList = new ArrayList<>();

    try (
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)
    ) {
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();

      while (rs.next()) {
        Map<String, Object> rowData = new LinkedHashMap<>();
        for (int i = 1; i <= columnCount; i++) {
          String columnName = metaData.getColumnName(i);
          Object columnValue = rs.getObject(i);
          rowData.put(columnName, columnValue);
        }
        resultList.add(rowData);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resultList;
  }

  /**
   * 执行查询，返回单条数据
   *
   * @param sql 查询 SQL
   * @return Map<String, Object> 查询结果，单行数据
   */
  public Map<String, Object> queryForSingle(String sql) {
    List<Map<String, Object>> resultList = queryForList(sql);
    return resultList.isEmpty() ? null : resultList.get(0);
  }

  /**
   * 执行更新操作（INSERT、UPDATE、DELETE）
   *
   * @param sql 更新 SQL
   * @return 受影响的行数
   */
  public int executeUpdate(String sql) {
    int affectedRows = 0;

    try (
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement()
    ) {
      affectedRows = stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return affectedRows;
  }
}
