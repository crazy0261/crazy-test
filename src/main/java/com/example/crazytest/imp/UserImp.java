package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.JWTUtil;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:02
 * @DESRIPTION
 */

@Service
public class UserImp implements UserService {

  @Autowired
  UserRepositoryService userRepositoryService;

  @Override
  public IPage<UserResultEntity> getUsers(String account, String name, String phone, Boolean status,
      Integer pageNum, Integer pageSize) {
    Page<User> page = new Page<>(pageNum, pageSize);
    IPage<User> userList = userRepositoryService.listAll(page, account, name, phone, status);

    IPage<UserResultEntity> result = new Page<>();
    result.setRecords(userList.getRecords().stream().map(user -> {
      UserResultEntity userResultEntity = new UserResultEntity();
      BeanUtils.copyProperties(user, userResultEntity);
      return userResultEntity;
    }).collect(Collectors.toList()));

    result.setTotal(userList.getTotal());
    result.setSize(userList.getSize());
    return result;
  }

  @Override
  public Boolean save(User user) {
    return userRepositoryService.saveOrUpdate(user);
  }

  @Override
  public Boolean resetPwd(String resetPwd) {
    return userRepositoryService.resetPwd(resetPwd);
  }

  @Override
  public String login(String account, String password) {

    AssertUtil.assertNotTrue(StringUtils.isNotEmpty(account) && StringUtils.isNotEmpty(password),
        ResultEnum.BAD_REQUEST.getMessage());
    User userEntity = userRepositoryService.getUser(account);

    AssertUtil.assertNotNull(userEntity, ResultEnum.USER_NOT_FOUND.getMessage());
    AssertUtil.assertNotTrue(Boolean.FALSE.equals(userEntity.getIsDelete()),
        ResultEnum.USER_STOP_STATUS.getMessage());
    AssertUtil.assertNotTrue(userEntity.getPassword().equals(password),
        ResultEnum.USER_PASSWORD_FAIL.getMessage());

    return JWTUtil.crateToken(userEntity);
  }

  @Override
  public UserResultEntity currentUser(String account) {
    UserResultEntity userEntity = new UserResultEntity();
    User user = userRepositoryService.currentUser(account);
    BeanUtils.copyProperties(user, userEntity);
    return userEntity;
  }
}
