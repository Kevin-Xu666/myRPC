package com.xzs.example.provider;

import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.RpcApplication;
import com.xzs.myrpc.config.RegistryConfig;
import com.xzs.myrpc.config.RpcConfig;
import com.xzs.myrpc.model.ServiceMetaInfo;
import com.xzs.myrpc.registry.LocalRegistry;
import com.xzs.myrpc.registry.Registry;
import com.xzs.myrpc.registry.RegistryFactory;
import com.xzs.myrpc.server.HttpServer;
import com.xzs.myrpc.server.VertxHttpServer;

public class ProviderExample {

    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();

        //注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
