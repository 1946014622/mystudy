package com.atguigu.spring_security.domain.exception;

import com.alibaba.fastjson.JSON;
import com.atguigu.spring_security.domain.response.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response<Object> RestResponse = new Response<>(403, "权限不足");
        String s = JSON.toJSONString(RestResponse);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
    }
}
