package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;

public interface UserService {

  IPage<UserResultEntity> getUsers(String account, String name, String phone, Boolean status, Integer page, Integer size);

  Boolean save(User user);

  Boolean resetPwd(String account);

  String login(String account, String password);

  UserResultEntity currentUser(String account);

}
