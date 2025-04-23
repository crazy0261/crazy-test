package com.example.crazytest.repository;

import com.example.crazytest.entity.DailyData;
import com.baomidou.mybatisplus.extension.service.IService;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 每日汇总数据 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-17
 */
public interface DailyDataRepositoryService extends IService<DailyData> {

  List<DailyData> getCoreIndicatorsList(Long projectId, LocalDate startDate, LocalDate endDate);

}
