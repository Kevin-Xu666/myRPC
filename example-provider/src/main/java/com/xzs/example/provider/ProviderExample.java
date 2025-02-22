package com.xzs.example.provider;

import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.RpcApplication;
import com.xzs.myrpc.registry.LocalRegistry;
import com.xzs.myrpc.server.HttpServer;
import com.xzs.myrpc.server.VertxHttpServer;

public class ProviderExample {

    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();

        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        //启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
