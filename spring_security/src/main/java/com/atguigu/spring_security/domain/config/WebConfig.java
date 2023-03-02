package com.atguigu.spring_security.domain.config;

import com.atguigu.spring_security.domain.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/doc.html/**");
        excludePathPatterns.add("/swagger-resources/**");
        excludePathPatterns.add("/v2/api-docs");
        excludePathPatterns.add("/error");
        excludePathPatterns.add("/webjars/**");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register")
                .excludePathPatterns(excludePathPatterns);
    }
}
