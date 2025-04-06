package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.mapper.ProcessCaseNodeMapper;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景测试用例节点详情 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Service
public class ProcessCaseNodeRepositoryServiceImpl extends ServiceImpl<ProcessCaseNodeMapper, ProcessCaseNode> implements
    ProcessCaseNodeRepositoryService {

}
