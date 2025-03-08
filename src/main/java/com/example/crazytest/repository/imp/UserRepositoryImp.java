package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.UserEntity;
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
public class UserRepositoryImp extends ServiceImpl<UserMapper, UserEntity> implements
    UserRepository {

  @Override
  public List<UserEntity> listAll() {
    return lambdaQuery().eq(UserEntity::getIsDelete, 0).list();
  }

  @Override
  public UserEntity getUser(String account) {
    return lambdaQuery().eq(UserEntity::getAccount, account).eq(UserEntity::getIsDelete, 0)
        .orderByDesc(UserEntity::getUpdateTime).last("limit 1").one();
  }

  @Override
  public Boolean resetPwd(String account) {

    return lambdaUpdate().eq(UserEntity::getAccount, account).eq(UserEntity::getIsDelete, 0)
        .set(UserEntity::getPassword, "crazy-test")
        .update();
  }
}
