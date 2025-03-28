package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.entity.req.ApiDebugReq;
import com.example.crazytest.vo.ApiCaseResultVo;
import com.example.crazytest.vo.ResultApiVO;

public interface ApiCaseResultService {

  ApiCaseResult queryById(Long id);

  IPage<ApiCaseResultVo> list(String apiTestcaseId, Integer current, Integer pageSize);

  boolean save(ApiDebugReq apiDebugReq,ResultApiVO resultApiVO);

}
