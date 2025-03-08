package com.example.crazytest.services;

import com.example.crazytest.entity.UserEntity;
import java.util.List;

public interface UserService {

  List<UserEntity> getUsers();

  Boolean save(UserEntity user);

  String login(String account,String password);

}
