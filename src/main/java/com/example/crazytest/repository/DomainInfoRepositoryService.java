package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.DomainInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 域名信息表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-21
 */
public interface DomainInfoRepositoryService extends IService<DomainInfo> {

  IPage<DomainInfo> list(Long projectId, String name, String urlPath, int current, int pageSize);

  List<DomainInfo> getByNameList(Long projectId, String name);

}
