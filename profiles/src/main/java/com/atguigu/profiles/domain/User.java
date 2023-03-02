package com.atguigu.profiles.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("person")
@Component
public class User {
    private String name;
    private Integer age;
}
