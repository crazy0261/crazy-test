package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.EncryptInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 加密参数表 服务类
 * </p>
 *
 * @author Menghui
 * @since 2025-04-09
 */
public interface EncryptInfoRepositoryService extends IService<EncryptInfo> {

  IPage<EncryptInfo> list(Long projectId, String name, Integer current, Integer pageSize);

  EncryptInfo getEncryptInfoEnv(Long projectId, Long id);

  List<EncryptInfo> getAppIds(Long projectId, Long appId);


}
