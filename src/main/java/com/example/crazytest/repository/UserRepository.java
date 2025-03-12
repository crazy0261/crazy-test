package com.example.crazytest.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.User;
import java.util.List;

public interface UserRepository extends IService<User> {

  List<User> listAll();

  User getUser(String account);

  Boolean resetPwd(String account);

  User currentUser(String account);
}
