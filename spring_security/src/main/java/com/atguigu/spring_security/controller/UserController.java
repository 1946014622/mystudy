package com.atguigu.spring_security.controller;

import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.response.Response;
import com.atguigu.spring_security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("login")
    public Response<Map<String,String>> login(@RequestBody SysUser sysUser){
        return userService.login(sysUser);
    }
    @PostMapping("test")
    public Response<Map<String,String>> test(String message){
        return new Response<>(200,message);
    }

    /**
     * 注册用户
     * @param sysUser
     * @return
     */
    @PostMapping("register")
    public Response register(@RequestBody SysUser sysUser){
        return userService.register(sysUser);
    }
}
