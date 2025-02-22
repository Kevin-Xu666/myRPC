package com.xzs.example.consumer;

import com.xzs.example.commom.model.User;
import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.config.RpcConfig;
import com.xzs.myrpc.proxy.ServiceProxyFactory;
import com.xzs.myrpc.utils.ConfigUtils;

public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc.getName());
        System.out.println(rpc.getServerPort());
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
