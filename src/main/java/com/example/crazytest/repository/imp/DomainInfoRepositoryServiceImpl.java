package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.DomainInfo;
import com.example.crazytest.mapper.DomainInfoMapper;
import com.example.crazytest.repository.DomainInfoRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 域名信息表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-03-21
 */
@Service
public class DomainInfoRepositoryServiceImpl extends
    ServiceImpl<DomainInfoMapper, DomainInfo> implements
    DomainInfoRepositoryService {

  @Override
  public IPage<DomainInfo> list(Long projectId, String name, String urlPath, int current,
      int pageSize) {
    return this.lambdaQuery()
        .eq(DomainInfo::getProjectId, projectId)
        .like(ObjectUtils.isNotNull(name), DomainInfo::getName, name)
        .like(ObjectUtils.isNotNull(urlPath), DomainInfo::getUrlPath, urlPath)
        .eq(DomainInfo::getIsDelete, Boolean.FALSE)
        .orderByDesc(DomainInfo::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public List<DomainInfo> getByNameList(Long projectId, String name) {
    return this.lambdaQuery()
        .eq(DomainInfo::getProjectId, projectId)
        .like(ObjectUtils.isNotNull(name), DomainInfo::getName, name)
        .list();
  }
}
