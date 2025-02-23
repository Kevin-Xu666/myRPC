package com.xzs.example.commom.service;

import com.xzs.example.commom.model.User;

public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 获取数字
     * @return
     */
    default short getNumber(){
        return 1;
    }
}
