package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.mapper.ApiCaseMapper;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试用例 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Service
public class ApiCaseServiceImpl extends ServiceImpl<ApiCaseMapper, ApiCase> implements
    ApiCaseService {

  @Autowired
  ApiCaseRepositoryService apiCaseRepository;

  @Override
  public IPage<ApiCase> list(ApiCaseReq apiCaseReq) {
    apiCaseRepository.list(apiCaseReq);

    return null;
  }
}
