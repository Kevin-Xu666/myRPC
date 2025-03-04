package com.xzs.myrpc.springboot.starter.annotation;

import com.xzs.myrpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.xzs.myrpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.xzs.myrpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用RPC注解
 */
@Target({ElementType.TYPE}) //该注解只能应用于类、接口（包括注解类型）或枚举声明。
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动Server
     * @return
     */
    boolean needServer() default true;
}
