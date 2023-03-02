package com.atguigu;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring.xml")
public class SpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        System.out.println(ioc.containsBean("createUser"));
    }
}
