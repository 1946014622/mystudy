package com.example.springboot_mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpringBoot {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        System.out.println(jdbcTemplate);
    }
}
