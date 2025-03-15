package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseReq;

public interface ApiCaseRepositoryService extends IService<ApiCase> {

  IPage<ApiCase> list(ApiCaseReq apiCaseReq);

}
