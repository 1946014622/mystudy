package com.atguigu.spring_security.service.impl;

import com.atguigu.spring_security.domain.entity.LoginUser;
import com.atguigu.spring_security.domain.entity.SysUser;
import com.atguigu.spring_security.domain.mapper.SysUserMapper;
import com.atguigu.spring_security.domain.response.Response;
import com.atguigu.spring_security.domain.utils.JwtUtil;
import com.atguigu.spring_security.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public SysUser getUser(int id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(Long.valueOf(id));
        return sysUser;
    }

    @Override
    public Response<Map<String,String>> login(SysUser sysUser) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword());
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (InternalAuthenticationServiceException e){
            return new Response<>(403,"账号或者密码不正确");
        }
        LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisTemplate.opsForValue().set(userId,loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return new Response(200,map);
    }

    /**
     * 用户注册
     */
    @Override
    public Response register(SysUser sysUser) {
        Assert.notNull(sysUser,"用户信息不能为空");
        String encode = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        sysUser.setCreateTime(new Date());
        boolean insert = sysUserMapper.insert(sysUser) > 0;
        if (insert) {
            return new Response<>(200,"注册成功");
        }
        return new Response<>(500,"注册失败");
    }
}
