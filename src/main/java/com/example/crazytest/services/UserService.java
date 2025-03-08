package com.example.crazytest.services;

import com.example.crazytest.entity.UserEntity;
import java.util.List;

public interface UserService {

  List<UserEntity> getUsers();

  Boolean save(UserEntity user);

  Boolean resetPwd(String account);

  String login(String account,String password);

}
