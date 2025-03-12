package com.example.crazytest.services;

import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import java.util.List;

public interface UserService {

  List<User> getUsers();

  Boolean save(User user);

  Boolean resetPwd(String account);

  String login(String account,String password);

  UserResultEntity currentUser(String account);

}
