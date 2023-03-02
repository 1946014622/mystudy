package com.atguigu.domin.config;

import com.atguigu.domin.entity.Car;
import com.atguigu.domin.entity.User;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration(proxyBeanMethods = false)
@Import({User.class,Thread.class})
public class Config {
    @Bean
    @ConditionalOnBean(Car.class)
    public User createUser(Car car){
        return new User("test",car);
    }
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }
}
