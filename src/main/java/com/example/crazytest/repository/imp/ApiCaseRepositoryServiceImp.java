package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/15 14:42
 * @DESRIPTION
 */

@Service
public class ApiCaseRepositoryServiceImp extends ServiceImpl<ApiCaseMapper, ApiCase> implements
    ApiCaseRepositoryService {

  @Autowired
  ApiCaseMapper apiCaseMapper;

  @Override
  public IPage<ApiCase> list(ApiCaseReq apiCaseReq) {
    LambdaQueryWrapper<ApiCase> wrapper = new LambdaQueryWrapper<ApiCase>()
        .eq(ApiCase::getIsDelete, 0)
        .orderByDesc(ApiCase::getModifyTime);
    return apiCaseMapper
        .selectPage(new Page<>(apiCaseReq.getCurrent(), apiCaseReq.getPageSize()), wrapper);
  }
}
