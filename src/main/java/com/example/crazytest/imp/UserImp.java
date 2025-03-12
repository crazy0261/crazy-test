package com.example.crazytest.imp;

import com.example.crazytest.entity.User;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.imp.UserRepositoryImp;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.JWTUtil;
import java.util.List;
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
  UserRepositoryImp userRepositoryImp;

  @Override
  public List<User> getUsers() {
    return userRepositoryImp.listAll();
  }

  @Override
  public Boolean save(User user) {
    return userRepositoryImp.saveOrUpdate(user);
  }

  @Override
  public Boolean resetPwd(String resetPwd) {
    return userRepositoryImp.resetPwd(resetPwd);
  }

  @Override
  public String login(String account, String password) {

    AssertUtil.assertNotTrue(account.isEmpty() || password.isEmpty(),
        ResultEnum.BAD_REQUEST.getMessage());
    User userEntity = userRepositoryImp.getUser(account);

    AssertUtil.assertNotNull(userEntity, ResultEnum.USER_NOT_FOUND.getMessage());
    AssertUtil.assertNotTrue(Boolean.TRUE.equals(userEntity.getIsDelete()),
        ResultEnum.USER_STOP_STATUS.getMessage());
    AssertUtil.assertNotTrue(userEntity.getPassword().equals(password),
        ResultEnum.USER_PASSWORD_FAIL.getMessage());

    return JWTUtil.crateToken(userEntity);
  }
}
