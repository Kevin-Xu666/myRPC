package com.xzs.examplespringbootprovider;

import com.xzs.example.commom.model.User;
import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
