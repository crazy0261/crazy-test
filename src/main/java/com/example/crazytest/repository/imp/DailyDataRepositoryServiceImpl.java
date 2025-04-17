package com.example.crazytest.repository.imp;

import com.example.crazytest.entity.DailyData;
import com.example.crazytest.mapper.DailyDataMapper;
import com.example.crazytest.repository.DailyDataRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.repository.UserRepositoryService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 每日汇总数据 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-17
 */
@Service
public class DailyDataRepositoryServiceImpl extends ServiceImpl<DailyDataMapper, DailyData> implements
    DailyDataRepositoryService {

  @Autowired
  UserRepositoryService userRepositoryService;

  @Override
  public List<DailyData> getCoreIndicatorsList(Long projectId, LocalDate startDate,
      LocalDate endDate) {
    return this.lambdaQuery()
        .eq(DailyData::getProjectId, projectId)
        .eq(DailyData::getIsDelete, 0)
        .between(DailyData::getDate, startDate, endDate)
        .list();
  }
}
