package com.xzs.example.commom.model;

import java.io.Serializable;

public class User implements Serializable { //对象需实现序列化接口，为后续网络传输序列化提供支持

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
