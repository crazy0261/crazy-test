package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.req.ApiCaseBatchReq;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.vo.ApiCaseVO;
import com.example.crazytest.vo.AssertVO;
import com.example.crazytest.vo.AssertResultVo;
import com.example.crazytest.vo.ResultApiVO;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试用例 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
public interface ApiCaseService extends IService<ApiCase> {

  IPage<ApiCaseVO> list(String name, Long appId, String path, Boolean status,
      String recentExecResult, Long ownerId, Integer current, Integer pageSize);

  ApiCaseVO getById(Long id);

  boolean save(ApiCaseReq apiCase);

  List<Map<String, Object>> allList();

  ResultApiVO debug(ApiDebugReq apiDebugReq) throws IOException;

  AssertResultVo assertResult(List<AssertVO> assertVOS, JSONObject body) throws IOException;

  Boolean checkAssertion(AssertVO assertVO, JSONObject body);

  Boolean assertConditionResult(String condition, String expectValue, String actualValue);

  Boolean copyApiCase(ApiDebugReq apiDebugReq);

  Boolean delete(ApiDebugReq apiCaseReq);

  Boolean batchOwner(ApiCaseBatchReq batchReq);

  Boolean batchUpdate(ApiCaseBatchReq batchReq);

  Boolean batchDown(ApiCaseBatchReq batchReq);

  List<Long> checkApiCaseEnable(List<Long> ids);

  JSONObject encryptParam(String secret, JSONObject params);

  JSONObject decryptRequestBody(String secret, JSONObject body);

  Long getCoverageIsApiCount();

  Map<Long,Integer> getAssetsNot();

  List<AssetsNotListEntity> getAssetsNotMap();


}
