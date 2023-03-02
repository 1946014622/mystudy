package com.example.springboot_mysql.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_mysql.domain.TUser;
import com.example.springboot_mysql.domain.User;
import com.example.springboot_mysql.service.UserService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {
    @Resource
    private User user;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private DruidDataSource dataSource;
    @Resource
    private UserService userService;
    @GetMapping("/getUser")
    public void test(){
        String sql = "select * from t_user where id = 1";
        List<TUser> users = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<TUser>(TUser.class));
        users.stream().forEach(System.out::println);
        System.out.println(dataSource);
    }
    @ResponseBody
    @GetMapping("/getTest")
    public TUser test2(){
        TUser byId = userService.getById(1);
        return byId;
    }
    @ResponseBody
    @GetMapping("/get")
    public User test4(){

        return user;
    }
    @ResponseBody
    @GetMapping("/addUser")
    public TUser addUser(){
        TUser tUser = new TUser();
        tUser.setAge(18);
        tUser.setDeptId(2);
        tUser.setName("老许");
        tUser.setPhone("184545454");
        tUser.setId(0);
        boolean b = userService.saveOrUpdate(tUser);
        return tUser;
    }
    @ResponseBody
    @GetMapping("/deleteUser")
    public TUser deleteUser(){
        TUser tUser = new TUser();
        tUser.setAge(18);
        tUser.setDeptId(2);
        tUser.setName("老许");
        tUser.setPhone("184545454");
        tUser.setId(2);
//        userService.remove(new QueryWrapper<TUser>(tUser,tUser.getId()));
        boolean b = userService.saveOrUpdate(tUser);
        return tUser;
    }
}
