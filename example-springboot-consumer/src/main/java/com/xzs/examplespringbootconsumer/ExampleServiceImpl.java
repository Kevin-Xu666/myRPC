package com.xzs.examplespringbootconsumer;

import com.xzs.example.commom.model.User;
import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("xzs");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
