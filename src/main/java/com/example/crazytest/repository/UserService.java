package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;

public interface UserService extends IService<User> {

  IPage<UserResultEntity> listAll(Page<User> page, String account, String name, String phone,
      Boolean status);

  User getUser(String account);

  Boolean resetPwd(String account);

  User currentUser(String account);
}
