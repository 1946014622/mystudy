package com.atguigu.spring_security.service;

import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.response.Response;

import java.util.Map;

public interface UserService {
    SysUser getUser(int id);

    Response<Map<String,String>> login(SysUser sysUser);

    Response<Object> register(SysUser sysUser);

    Response.success<Object> exit(Long userId);
}
