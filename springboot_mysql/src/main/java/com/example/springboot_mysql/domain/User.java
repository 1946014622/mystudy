package com.example.springboot_mysql.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("person")
public class User {
    private String name;
    private Integer age;
}
