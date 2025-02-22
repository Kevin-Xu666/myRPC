package com.xzs.example.provider;

import com.xzs.example.commom.model.User;
import com.xzs.example.commom.service.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
