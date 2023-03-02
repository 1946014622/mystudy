package com.atguigu.mapper.impl;

import com.atguigu.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapperImpl implements UserMapper {
    @Override
    public void sayHello() {
        System.out.println("你好");
    }
    @Override
    public void sayHello1() {
        System.out.println("你好a");
    }
}
