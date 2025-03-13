package com.example.crazytest.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import java.util.List;

public interface UserRepository extends IService<User> {

  List<UserResultEntity> listAll(String account, String name, String phone, Boolean status);

  User getUser(String account);

  Boolean resetPwd(String account);

  User currentUser(String account);
}
