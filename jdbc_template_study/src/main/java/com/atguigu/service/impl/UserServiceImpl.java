package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(int count) {
        if (count > 10) {
            throw new RuntimeException("超重了");
        }
        userDao.insert();
    }
}
