package com.xzs.example.provider;

import com.xzs.example.commom.service.UserService;
import com.xzs.myrpc.RpcApplication;
import com.xzs.myrpc.bootstrap.ProviderBootstrap;
import com.xzs.myrpc.config.RegistryConfig;
import com.xzs.myrpc.config.RpcConfig;
import com.xzs.myrpc.model.ServiceMetaInfo;
import com.xzs.myrpc.model.ServiceRegisterInfo;
import com.xzs.myrpc.registry.LocalRegistry;
import com.xzs.myrpc.registry.Registry;
import com.xzs.myrpc.registry.RegistryFactory;
import com.xzs.myrpc.server.HttpServer;
import com.xzs.myrpc.server.VertxHttpServer;
import com.xzs.myrpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

public class ProviderExample {

    public static void main(String[] args) {
        //要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<?> serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        //服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
