package com.example.crazytest.repository;

import com.example.crazytest.entity.Node;
import com.example.crazytest.entity.ProcessCaseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 场景用例执行结果 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
public interface ProcessCaseResultRepositoryService extends IService<ProcessCaseResult> {

  List<ProcessCaseResult> list(Long projectId, String status);

  ProcessCaseResult lastResult(Long projectId, Long caseId);

  void updateNodes(Long id, String nodes, String status);


}
