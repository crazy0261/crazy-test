package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiCaseRecord;
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
public interface ApiCaseResultRepositoryService extends IService<ApiCaseRecord> {

  IPage<ApiCaseRecord> list(String apiTestcaseId, Integer current, Integer pageSize);

  ApiCaseRecord lastApiCaseResult(Long apiTestcaseId);

  List<ApiCaseRecord> listResult(Long projectId, String recentExecResult);


}
