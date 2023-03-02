package com.atguigu.profiles.controller;

import com.atguigu.application.HelloService;
import com.atguigu.profiles.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("test")
@RestController
public class UserController {
    @Resource
    private User user;
    @Resource
    HelloService helloService;
    @GetMapping("getUser")
    public User getUser(){
        return user;
    }
    @GetMapping("getTest")
    public User test(){
        helloService.sayHello();
        return user;
    }
}
