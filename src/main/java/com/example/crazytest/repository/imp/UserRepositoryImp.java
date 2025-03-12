package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.User;
import com.example.crazytest.mapper.user.UserMapper;
import com.example.crazytest.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 00:20
 * @DESRIPTION
 */

@Service
public class UserRepositoryImp extends ServiceImpl<UserMapper, User> implements
    UserRepository {

  @Override
  public List<User> listAll() {
    return lambdaQuery().eq(User::getIsDelete, 0).list();
  }

  @Override
  public User getUser(String account) {
    return lambdaQuery().eq(User::getAccount, account)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }

  @Override
  public Boolean resetPwd(String account) {

    return lambdaUpdate().eq(User::getAccount, account).eq(User::getIsDelete, 0)
        .set(User::getPassword, "crazy-test")
        .update();
  }

  @Override
  public User currentUser(String account) {
    return lambdaQuery().eq(User::getAccount, account).eq(User::getIsDelete, 0)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }
}
