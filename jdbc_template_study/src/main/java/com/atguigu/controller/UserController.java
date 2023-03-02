package com.atguigu.controller;

import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(){
        int count = 0;
        while(true){
            count ++;
            userService.insert(count);

        }

    }
}
