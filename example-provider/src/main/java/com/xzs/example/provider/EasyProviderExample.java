package com.xzs.example.provider;

import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.registry.LocalRegistry;
import com.xzs.myrpc.server.HttpServer;
import com.xzs.myrpc.server.VertxHttpServer;

public class EasyProviderExample {

    public static void main(String[] args){
        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
