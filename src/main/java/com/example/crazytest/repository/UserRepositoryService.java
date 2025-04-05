package com.example.crazytest.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.User;
import java.util.List;

public interface UserRepositoryService extends IService<User> {

  IPage<User> listAll(Page<User> page, String account, String name, String phone,
      Boolean status);

  User getUser(String account);

  Boolean resetPwd(String account);

  User currentUser(String account);

  User getUserData(Long id);

  List<User> getNameList(String name);

  List<User> listAccount(String account);

  List<User> listAllAccount();


}
