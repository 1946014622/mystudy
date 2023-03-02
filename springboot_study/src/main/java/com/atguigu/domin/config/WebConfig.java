package com.atguigu.domin.config;

import com.atguigu.interceptor.HelloInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${path.file}")
    private String filepath;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HelloInterceptor helloInterceptor = new HelloInterceptor();
        registry.addInterceptor(helloInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/test1/**")
                .addResourceLocations("file:classpath:/test/**");
        registry.addResourceHandler("/**")
                .addResourceLocations("file:"+filepath);
    }
}
