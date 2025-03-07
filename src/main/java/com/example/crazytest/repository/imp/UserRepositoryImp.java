package com.example.crazytest.repository.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crazytest.entity.UserEntity;
import com.example.crazytest.mapper.user.UserMapper;
import com.example.crazytest.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 00:20
 * @DESRIPTION
 */

@Service
public class UserRepositoryImp extends ServiceImpl<UserMapper, UserEntity> implements
    UserRepository {

}
