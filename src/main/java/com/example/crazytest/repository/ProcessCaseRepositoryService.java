package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.entity.ProcessCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.req.ProcessCaseReq;
import java.util.List;

/**
 * <p>
 * 场景用例 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
public interface ProcessCaseRepositoryService extends IService<ProcessCase> {

  IPage<ProcessCase> listPage(ProcessCaseDTO processCaseDTO, Long projectId, List<Long> ids);
}
