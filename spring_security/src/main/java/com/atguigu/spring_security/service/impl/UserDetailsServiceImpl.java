package com.atguigu.spring_security.service.impl;

import com.atguigu.spring_security.domain.entity.LoginUser;
import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.mapper.SysUserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.queryByUserName(username);
        if (ObjectUtils.isEmpty(sysUser)) {
            log.error("账号或者密码错误");
            return null;
        }
        return new LoginUser(sysUser);
    }

}
