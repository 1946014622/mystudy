package com.atguigu.controller;

import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

    private UserService userService;
    @Resource
//    @Qualifier("userService2")
    public void setUserService1(UserService userService1) {
        this.userService = userService1;
    }

    public void sayHello(){
        userService.sayHello();
    }
}
