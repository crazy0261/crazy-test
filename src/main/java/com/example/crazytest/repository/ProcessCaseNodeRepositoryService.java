package com.example.crazytest.repository;

import com.example.crazytest.entity.ProcessCaseNode;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 场景测试用例节点详情 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
public interface ProcessCaseNodeRepositoryService extends IService<ProcessCaseNode> {

  ProcessCaseNode detail(Long projectId, Long id);

  List<ProcessCaseNode> getAssetsNotList(Long projectId);


}
