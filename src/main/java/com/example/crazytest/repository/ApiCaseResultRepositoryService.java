package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 接口执行结果 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
public interface ApiCaseResultRepositoryService extends IService<ApiCaseRecord> {

  IPage<ApiCaseRecord> list(String apiTestcaseId, Integer current, Integer pageSize);

  ApiCaseRecord lastApiCaseResult(Long apiTestcaseId);

  List<ApiCaseRecord> listResult(Long projectId, String recentExecResult);

  List<ApiCaseRecord> lastExecResult(Long projectId, Long scheduleBatchId);

  IPage<ApiCaseRecord> resultList(Long projectId, List<Long> ids, Integer current,
      Integer pageSize);

  List<ApiCaseRecord> getResultChildren(Long projectId, Long scheduleBatchId, Long apiTestcaseId, Long id);

  List<ApiCaseRecord> getApiCaseCount(Long projectId);

  List<ApiCaseRecord> getApiCaseFailed(Long projectId, LocalDateTime time);


}
