package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.EnvConfig;
import com.example.crazytest.mapper.EnvConfigMapper;
import com.example.crazytest.repository.EnvConfigRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 环境信息表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-19
 */
@Service
public class EnvConfigRepositoryServiceImpl extends ServiceImpl<EnvConfigMapper, EnvConfig> implements
    EnvConfigRepositoryService {

}
