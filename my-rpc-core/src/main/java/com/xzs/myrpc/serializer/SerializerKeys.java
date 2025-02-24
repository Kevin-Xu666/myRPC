package com.xzs.myrpc.serializer;

/**
 * 序列化器名，会作为动态选择时的key
 */
public interface SerializerKeys {

    String JDK = "jdk";
    String JSON = "json";
    String KRYO = "kryo";
    String HESSIAN = "hessian";
}
