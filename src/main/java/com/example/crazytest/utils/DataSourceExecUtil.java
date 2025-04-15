package com.example.crazytest.utils;

import com.alibaba.fastjson.JSONObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author
 * @name Menghui
 * @date 2025/4/16 01:11
 * @DESRIPTION 动态数据库执行
 */

public class DataSourceExecUtil {

  private DataSourceExecUtil() {
  }

  /**
   * 解析结果集
   *
   * @param rs        数据库查询结果
   * @param rowMapper 行映射
   * @param <T>       目标类型
   * @return
   * @throws SQLException
   */
  public static <T> Object parseResultSet(ResultSet rs, Function<ResultSet, T> rowMapper)
      throws SQLException {

    // 判断是否有结果
    if (!rs.next()) {
      return null;
    }

    // 判断是否为单行结果
    boolean isSingleRow = true;
    // rs.isFirst()判断游标第一行且下一行有数据
    if (rs.isFirst() && rs.next()) {
      isSingleRow = false;
      rs.previous(); // 移动游标第一行
    }

    if (isSingleRow) {
      return rowMapper.apply(rs);
    } else {
      return processRows(rs, rowMapper);
    }
  }

  /**
   * 处理多行结果
   *
   * @param rs        数据库结果
   * @param rowMapper 行映射
   * @param <T>       目标类型
   * @return
   * @throws SQLException
   */
  private static <T> List<T> processRows(ResultSet rs, Function<ResultSet, T> rowMapper)
      throws SQLException {
    List<T> resultList = new ArrayList<>();
    while (rs.next()) {
      resultList.add(rowMapper.apply(rs));
    }
    return resultList;
  }

  /**
   * 默认行映射
   *
   * @param rs
   * @return
   * @throws SQLException
   */
  public static JSONObject defaultRowMapper(ResultSet rs) {
    JSONObject json = new JSONObject();
    try {
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        String columnName = metaData.getColumnName(i);
        Object columnValue = rs.getObject(i);
        json.put(columnName, columnValue);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return json;
  }
}
