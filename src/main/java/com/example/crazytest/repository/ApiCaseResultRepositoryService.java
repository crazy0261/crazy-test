package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 接口执行结果 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-23
 */
public interface ApiCaseResultRepositoryService extends IService<ApiCaseResult> {

  IPage<ApiCaseResult> list(String apiTestcaseId, Integer current, Integer pageSize);

  ApiCaseResult lastApiCaseResult(Long apiTestcaseId);

  List<ApiCaseResult> listResult(String tenantId, String recentExecResult);


}
