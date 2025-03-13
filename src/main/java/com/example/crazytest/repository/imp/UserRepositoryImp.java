package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import com.example.crazytest.mapper.user.UserMapper;
import com.example.crazytest.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
  public List<UserResultEntity> listAll(String account, String name, String phone, Boolean status) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
        .like(StringUtils.isNotEmpty(account), User::getAccount, account)
        .like(StringUtils.isNotEmpty(name), User::getName, name)
        .eq(StringUtils.isNotEmpty(phone), User::getPhone, phone)
        .eq(status != null, User::getStatus, status)
        .eq(User::getIsDelete, 0)
        .orderByDesc(User::getUpdateTime);

    List<User> userList = list(wrapper);
    return userList.stream().map(user -> {
      UserResultEntity userResultEntity = new UserResultEntity();
      BeanUtils.copyProperties(user, userResultEntity);
      return userResultEntity;
    }).collect(Collectors.toList());
  }

  @Override
  public User getUser(String account) {
    return lambdaQuery().eq(User::getAccount, account)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }

  @Override
  public Boolean resetPwd(String account) {

    return lambdaUpdate().eq(User::getAccount, account).eq(User::getIsDelete, 0)
        .set(User::getPassword, "297aae72cc4d0d068f46a9158469e34d")
        .update();
  }

  @Override
  public User currentUser(String account) {
    return lambdaQuery().eq(User::getAccount, account).eq(User::getIsDelete, 0)
        .orderByDesc(User::getUpdateTime).last("limit 1").one();
  }
}
