package com.atguigu.service.impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService2")
public class UserServiceImpl2 implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void sayHello() {
        userMapper.sayHello1();
    }
}
