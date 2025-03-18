package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.User;
import com.example.crazytest.mapper.UserMapper;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.utils.BaseContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 00:20
 * @DESRIPTION
 */

@Service
public class UserRepositoryRepositoryServiceImp extends ServiceImpl<UserMapper, User> implements
    UserRepositoryService {

  @Autowired
  UserMapper userMapper;

  @Override
  public IPage<User> listAll(Page<User> page, String account, String name, String phone,
      Boolean status) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
        .like(StringUtils.isNotEmpty(account), User::getAccount, account)
        .like(StringUtils.isNotEmpty(name), User::getName, name)
        .eq(StringUtils.isNotEmpty(phone), User::getPhone, phone)
        .eq(User::getTenantId, BaseContext.getTenantId())
        .eq(status != null, User::getStatus, status)
        .eq(User::getIsDelete, Boolean.FALSE)
        .orderByDesc(User::getUpdateTime);
    return userMapper.selectPage(page, wrapper);
  }

  @Override
  public User getUser(String account) {
    return lambdaQuery().eq(User::getAccount, account)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }

  @Override
  public Boolean resetPwd(String account) {

    return lambdaUpdate().eq(User::getAccount, account).eq(User::getIsDelete, Boolean.FALSE)
        .set(User::getPassword, "297aae72cc4d0d068f46a9158469e34d")
        .update();
  }

  @Override
  public User currentUser(String account) {
    return lambdaQuery().eq(User::getAccount, account).eq(User::getIsDelete, Boolean.FALSE)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }
}
