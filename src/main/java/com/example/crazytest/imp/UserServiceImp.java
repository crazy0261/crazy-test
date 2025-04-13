package com.example.crazytest.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.req.UserResultEntity;
import com.example.crazytest.enums.ResultEnum;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ProjectUserAssociationService;
import com.example.crazytest.services.UserService;
import com.example.crazytest.utils.AssertUtil;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.JWTUtil;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:02
 * @DESRIPTION
 */

@Service
public class UserServiceImp implements UserService {

  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  ProjectUserAssociationService projectUserAssociationService;

  @Value("${user.resetPwd}")
  String password;

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
    user.setPassword(Objects.isNull(user.getPassword()) ? password : user.getPassword());
    Boolean checkAccount = checkUser(user.getAccount());
    AssertUtil.assertTrue(checkAccount, ResultEnum.USER_EXIST_FAIL.getMessage());
    return userRepositoryService.saveOrUpdate(user);
  }

  @Override
  public Boolean resetPwd(String resetPwd) {
    return userRepositoryService.resetPwd(resetPwd);
  }

  @Override
  public void loginCheck(User userEntity, String password) {
    AssertUtil.assertNotNull(userEntity, ResultEnum.USER_NOT_FOUND.getMessage());
    AssertUtil.assertNotTrue(Boolean.FALSE.equals(userEntity.getIsDelete()),
        ResultEnum.USER_STOP_STATUS.getMessage());
    AssertUtil.assertNotTrue(Objects.equals(userEntity.getPassword(), password),
        ResultEnum.USER_PASSWORD_FAIL.getMessage());
  }

  @Override
  public String updateToken(Long selectProject) {
    User userEntity = userRepositoryService.getUserData(BaseContext.getUserId());

    userEntity.setSelectProject(selectProject);
    userRepositoryService.updateById(userEntity);

    return JWTUtil.crateToken(userEntity);
  }

  @Override
  public String login(String account, String password) {

    AssertUtil.assertNotTrue(StringUtils.isNotEmpty(account) && StringUtils.isNotEmpty(password),
        ResultEnum.BAD_REQUEST.getMessage());
    User userEntity = userRepositoryService.getUser(account);
    loginCheck(userEntity, password);

    userEntity.setSelectProject(Optional.ofNullable(userEntity.getSelectProject())
        .orElseGet(() -> projectUserAssociationService.getOne(userEntity.getId()).getProjectId()));

    userRepositoryService.updateById(userEntity);

    return JWTUtil.crateToken(userEntity);
  }

  @Override
  public UserResultEntity currentUser(String account) {
    UserResultEntity userEntity = new UserResultEntity();
    User user = userRepositoryService.currentUser(account);
    BeanUtils.copyProperties(user, userEntity);
    return userEntity;
  }

  @Override
  public User getById(Long id) {
    return userRepositoryService.getById(id);
  }

  @Override
  public List<Long> getNameList(String name) {
    List<User> userList = userRepositoryService.getNameList(name);
    return userList.stream().map(User::getId).collect(Collectors.toList());
  }

  @Override
  public Boolean delete(Long id) {
    return userRepositoryService.removeById(id);
  }

  @Override
  public Boolean checkUser(String account) {
    List<User> userList = userRepositoryService.listAccount(account);
    return userList.isEmpty();
  }

  @Override
  public List<UserResultEntity> listAll() {
    List<User> userList = userRepositoryService.listAllAccount();

    return userList.stream()
        .map(user -> {
          UserResultEntity userResultEntity = new UserResultEntity();
          BeanUtils.copyProperties(user, userResultEntity);
          return userResultEntity;
        }).collect(Collectors.toList());
  }
}
