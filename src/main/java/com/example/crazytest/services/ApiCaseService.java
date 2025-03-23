package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ApiCaseReq;
import com.example.crazytest.vo.ApiCaseVO;

/**
 * <p>
 * 测试用例 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
public interface ApiCaseService extends IService<ApiCase> {

  IPage<ApiCaseVO> list(String name, Long appId,String path, Boolean status, String recentExecResult, Long ownerId, Integer current, Integer pageSize);

  ApiCaseVO getById(Long id);

  boolean save(ApiCase apiCase);

}
