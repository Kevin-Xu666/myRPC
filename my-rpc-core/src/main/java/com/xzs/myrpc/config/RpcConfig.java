package com.xzs.myrpc.config;

import lombok.Data;

/**
 * RPC框架配置项
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "my-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private int serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;
}
