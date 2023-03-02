package com.atguigu.service.impl;

import com.atguigu.annotation.HelloAnnotation;
import com.atguigu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    @HelloAnnotation
    public void test() {
        System.out.println("调用了test方法");
    }
}
