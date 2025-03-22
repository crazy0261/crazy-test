package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ApiCaseReq;

/**
 * <p>
 * 测试用例 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
public interface ApiCaseService extends IService<ApiCase> {

  IPage<ApiCase> list(ApiCaseReq apiCaseReq);

  ApiCase getById(Long id);

}
