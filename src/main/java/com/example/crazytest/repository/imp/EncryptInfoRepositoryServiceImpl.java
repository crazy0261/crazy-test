package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.EncryptInfo;
import com.example.crazytest.mapper.EncryptInfoMapper;
import com.example.crazytest.repository.EncryptInfoRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.vo.EncryptInfoVO;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 加密参数表 服务实现类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-09
 */
@Service
public class EncryptInfoRepositoryServiceImpl extends
    ServiceImpl<EncryptInfoMapper, EncryptInfo> implements
    EncryptInfoRepositoryService {

  @Override
  public IPage<EncryptInfo> list(Long projectId, String name, Integer current, Integer pageSize) {
    return this.lambdaQuery()
        .eq(EncryptInfo::getProjectId, projectId)
        .like(Objects.nonNull(name), EncryptInfo::getName, name)
        .eq(EncryptInfo::getIsDelete, Boolean.FALSE)
        .orderByDesc(EncryptInfo::getUpdateTime)
        .page(new Page<>(current, pageSize));
  }

  @Override
  public EncryptInfo getEncryptInfoEnv(Long projectId, Long id) {
    return this.lambdaQuery()
        .eq(EncryptInfo::getProjectId, projectId)
        .eq(EncryptInfo::getId, id)
        .eq(EncryptInfo::getIsDelete, Boolean.FALSE)
        .one();
  }

  @Override
  public List<EncryptInfo> getAppIds(Long projectId, Long appId) {
    return this.lambdaQuery()
        .eq(EncryptInfo::getProjectId, projectId)
        .eq(EncryptInfo::getAppId, appId)
        .eq(EncryptInfo::getIsDelete, Boolean.FALSE)
        .list();
  }
}
