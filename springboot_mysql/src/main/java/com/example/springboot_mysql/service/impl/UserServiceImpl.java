package com.example.springboot_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_mysql.domain.TUser;
import com.example.springboot_mysql.mapper.UserMapper;
import com.example.springboot_mysql.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements UserService {
}
