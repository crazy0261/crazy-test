package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.ProcessCaseNode;
import com.example.crazytest.mapper.ProcessCaseNodeMapper;
import com.example.crazytest.repository.ProcessCaseNodeRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
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
public class ProcessCaseNodeRepositoryServiceImpl extends
    ServiceImpl<ProcessCaseNodeMapper, ProcessCaseNode> implements
    ProcessCaseNodeRepositoryService {

  @Override
  public ProcessCaseNode detail(Long projectId, Long id) {
    return this.lambdaQuery()
        .eq(ProcessCaseNode::getProjectId, projectId)
        .eq(ProcessCaseNode::getId, id)
        .eq(ProcessCaseNode::getIsDelete, Boolean.FALSE)
        .one();
  }

  @Override
  public List<ProcessCaseNode> getAssetsNotList(Long projectId) {
    return this.lambdaQuery()
        .eq(ProcessCaseNode::getProjectId, projectId)
        .ne(ProcessCaseNode::getAssertList, "[]")
        .eq(ProcessCaseNode::getIsDelete, Boolean.FALSE)
        .list();
  }
}
