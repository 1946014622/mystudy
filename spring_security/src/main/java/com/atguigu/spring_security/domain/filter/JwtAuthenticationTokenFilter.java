package com.atguigu.spring_security.domain.filter;

import com.atguigu.spring_security.domain.entity.LoginUser;
import com.atguigu.spring_security.domain.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    @Resource
//    private RedisTemplate redisTemplate;
//    @SneakyThrows
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
//        String token = request.getHeader("token");
//        if (!StringUtils.hasLength(token)) {
//            filterChain.doFilter(request,response);
//            return;
//        }
//        Claims claims = JwtUtil.parseJWT(token);
//        String subject = claims.getSubject();
//        LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(subject);
//        if (ObjectUtils.isEmpty(loginUser)) {
//            throw new RuntimeException("用户未登录");
//        }
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken(loginUser,null,null);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request,response);
//    }
//}
