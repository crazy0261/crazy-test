package com.example.crazytest.services.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.dto.DataSourceDTO;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.entity.req.DataSourceConfigReq;
import com.example.crazytest.enums.ExecStatusEnum;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.DataSourceConfigRepositoryService;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.DataSourceConfigService;
import com.example.crazytest.services.NodeService;
import com.example.crazytest.utils.DataSourceExecUtil;
import com.example.crazytest.utils.DataSourceUtil;
import com.example.crazytest.utils.JSONPathUtil;
import com.example.crazytest.utils.VariablesUtil;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.AssertVO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/14 21:29
 * @DESRIPTION sql 节点实现
 */

@Service
public class SqlNodeServiceImp implements NodeService {

  @Autowired
  ProcessCaseNodeRepositoryService processCaseNodeRepositoryService;

  @Autowired
  DataSourceConfigRepositoryService dataSourceConfigRepositoryService;

  @Autowired
  DataSourceConfigService dataSourceConfigService;

  @Autowired
  ApiCaseService apiCaseService;

  @Override
  public ExecutionResult execute(Node node, ExecutionProcessContext context) throws SQLException {
    ExecutionResult result = new ExecutionResult();
    ProcessCaseNode processCaseNode = processCaseNodeRepositoryService
        .detail(context.getProjectId(), Long.valueOf(node.getId()));

    DataSourceDTO dto = dataSourceConfigService
        .getDataSource(processCaseNode.getDataSourceId(), context.getApiDebugReq().getEnvId());
    DataSourceConfigReq dataSourceConfigReq = new DataSourceConfigReq();
    BeanUtils.copyProperties(dto, dataSourceConfigReq);

    DataSource dataSource = DataSourceUtil.createDataSource(dataSourceConfigReq);

    String sqlStr = VariablesUtil
        .replacePlaceholders(processCaseNode.getSqlScript(), context.getEnvParameter());
    String sql = sqlStr.toUpperCase();
    if (!sql.startsWith("SELECT")) {
      result.setStatus(ExecStatusEnum.FAILED);
      result.setSqlExecResult(ResultEnum.SQL_NOT_SELECT.getMessage());
      return result;
    }

    // 测试连接
    assert dataSource != null;

    try (Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt
            .executeQuery(sql.contains("LIMIT") ? sqlStr : sqlStr.concat(" LIMIT 100"));
    ) {
      Object resultSet = DataSourceExecUtil
          .parseResultSet(rs, DataSourceExecUtil::defaultRowMapper);

      assert resultSet != null;
      result.setSqlExecResult(resultSet.toString());

      List<AssertVO> assets = Optional.ofNullable(processCaseNode.getAssertList()).map(
          str -> JSON.parseArray(str, AssertVO.class)).orElse(Collections.emptyList());

      AssertResultVo assertResultVo = apiCaseService
          .assertResult(assets, JSON.parseObject(resultSet.toString()));
      result.setMessage(JSON.toJSONString(assertResultVo));
      result.setStatus(Boolean.TRUE.equals(assertResultVo.getPass()) ? ExecStatusEnum.SUCCESS
          : ExecStatusEnum.FAILED);

      Map<String, String> envParameter = context.getEnvParameter();
      JSONObject outParam = Optional.ofNullable(processCaseNode.getOutputParams()).map(
          JSON::parseObject).orElse(new JSONObject());

      outParam.forEach((key, value) -> envParameter.put(key,JSONPathUtil.isJsonPathValue(JSON.parseObject(resultSet.toString()),value.toString())));

      context.setEnvParameter(envParameter);
    } catch (SQLException | IOException e) {
      result.setStatus(ExecStatusEnum.FAILED);
    }
    return result;
  }
}
