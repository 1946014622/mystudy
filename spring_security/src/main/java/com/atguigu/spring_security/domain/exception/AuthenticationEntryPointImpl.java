package com.atguigu.spring_security.domain.exception;

import com.alibaba.fastjson.JSON;
import com.atguigu.spring_security.domain.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Response<Object> restResponse = new Response<>(HttpStatus.UNAUTHORIZED.value(), "认证失败，请重新登录");
        String json = JSON.toJSONString(restResponse);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(json);
    }
}
