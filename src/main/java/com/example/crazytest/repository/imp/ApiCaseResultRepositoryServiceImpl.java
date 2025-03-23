package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ApiCaseResult;
import com.example.crazytest.mapper.ApiCaseResultMapper;
import com.example.crazytest.repository.ApiCaseResultRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口执行结果 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
@Service
public class ApiCaseResultRepositoryServiceImpl extends ServiceImpl<ApiCaseResultMapper, ApiCaseResult> implements
    ApiCaseResultRepositoryService {

}
