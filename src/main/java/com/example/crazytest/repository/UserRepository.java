package com.example.crazytest.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crazytest.entity.UserEntity;
import java.util.List;

public interface UserRepository extends IService<UserEntity> {

  List<UserEntity> listAll();

  UserEntity getUser(String account);

}
