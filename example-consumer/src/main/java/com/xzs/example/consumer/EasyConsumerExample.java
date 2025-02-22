package com.xzs.example.consumer;

import com.xzs.example.commom.model.User;
import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {

    public static void main(String[] args){
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("xzs");
        //调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
