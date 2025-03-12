package com.example.crazytest.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crazytest.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
