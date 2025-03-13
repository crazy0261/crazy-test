package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ApiManage;
import com.example.crazytest.mapper.ApiManageMapper;
import com.example.crazytest.repository.IApiManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口管理表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-13
 */
@Service
public class ApiManageServiceImpl extends ServiceImpl<ApiManageMapper, ApiManage> implements IApiManageService {

}
