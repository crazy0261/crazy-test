package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.User;
import com.example.crazytest.mapper.UserMapper;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.utils.BaseContext;
import java.util.List;
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
        .set(User::getPassword, "2667544fa2fa43948243f8871dfc9139")
        .update();
  }

  @Override
  public User currentUser(String account) {
    return lambdaQuery().eq(User::getAccount, account).eq(User::getIsDelete, Boolean.FALSE)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }

  @Override
  public User getUserData(Long id) {
    return lambdaQuery().eq(User::getId, id).eq(User::getIsDelete, Boolean.FALSE).one();
  }

  @Override
  public List<User> getNameList(String name) {

    return this.lambdaQuery()
        .like(StringUtils.isNotEmpty(name), User::getName, name)
        .list();
  }

  @Override
  public List<User> listAccount(String account) {
    return this.lambdaQuery()
        .eq(User::getAccount, account)
        .eq(User::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public List<User> listAllAccount() {
    return this.lambdaQuery()
        .eq(User::getIsDelete, Boolean.FALSE)
        .list();
  }

  @Override
  public Long getUserCount(Long projectId) {
    return this.lambdaQuery()
        .eq(User::getSelectProject, projectId)
        .eq(User::getIsDelete, Boolean.FALSE)
        .count();
  }

}
