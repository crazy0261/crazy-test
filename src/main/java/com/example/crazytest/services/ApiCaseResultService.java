package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseRecord;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.entity.req.ApiCaseResultReq;
import com.example.crazytest.vo.ResultApiVO;
import java.util.List;

public interface ApiCaseResultService {

  ApiCaseRecord queryById(Long id);

  IPage<ApiCaseResultReq> list(String apiTestcaseId, Integer current, Integer pageSize);

  boolean save(ApiDebugReq apiDebugReq,ResultApiVO resultApiVO);

  List<Long> listResult(String tenantId, String recentExecResult);
}
