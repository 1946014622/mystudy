package com.atguigu.spring_security.controller;

import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.response.Response;
import com.atguigu.spring_security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("login")
    public Response<Map<String,String>> login(@RequestBody SysUser sysUser){
        return userService.login(sysUser);
    }

    @PreAuthorize("hasRole('vip')")
    @PostMapping("test")
    public Response<Map<String,String>> test(String message){
        return new Response<>(200,message);
    }

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Response<Object> register(@RequestBody SysUser sysUser){
        return userService.register(sysUser);
    }
    /**
     * 退出登录
     */
    @PostMapping("exit/{userId}")
    public Response.success<Object> exit(@PathVariable("userId") Long userId){
        return userService.exit(userId);
    }
}
