package com.atguigu.spring_security.controller;

import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.response.Response;
import com.atguigu.spring_security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserService userService;
    @GetMapping({"/hello","/test"})
    public Response hello(int id){
        SysUser sysUser = userService.getUser(id);
        return new Response<>(200,sysUser);
    }
}
