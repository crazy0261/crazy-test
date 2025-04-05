package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import java.util.List;

public interface UserService {

  IPage<UserResultEntity> getUsers(String account, String name, String phone, Boolean status,
      Integer page, Integer size);

  Boolean save(User user);

  Boolean resetPwd(String account);

  void loginCheck(User userEntity, String password);

  String updateToken(Long selectProject);

  String login(String account, String password);

  UserResultEntity currentUser(String account);

  User getById(Long id);

  List<Long> getNameList(String name);

  Boolean delete(Long id);

  Boolean checkUser(String account);

  List<UserResultEntity> listAll();

}
