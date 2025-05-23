package com.example.crazytest.services;

import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.req.ProcessCaseNodeReq;
import com.example.crazytest.vo.ProcessCaseNodeVO;
import java.util.List;
import java.util.Map;

public interface ProcessCaseNodeService {

  ProcessCaseNodeVO detail(Long id);

  Boolean save(ProcessCaseNodeReq processCaseNodeReq);

  String getNodeName(List<JSONObject> nodes, Long nodeId);

  Map<Long, Integer> getAssetsNotCount();

  List<AssetsNotListEntity> getAssetsNotMap();

}
