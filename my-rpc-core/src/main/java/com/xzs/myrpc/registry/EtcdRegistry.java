package com.xzs.myrpc.registry;

import cn.hutool.json.JSONUtil;
import com.xzs.myrpc.config.RegistryConfig;
import com.xzs.myrpc.model.ServiceMetaInfo;
import io.etcd.jetcd.*;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.PutOption;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Etcd注册中心
 */
public class EtcdRegistry implements Registry {

    private Client client; //Etcd客户端

    private KV kvClient; //Etcd键值操作客户端

    /**
     * 定义Etcd键存储的根节点
     */
    private static final String ETCD_ROOT_PATH = "/rpc/";

    /**
     * 初始化Etcd客户端
     * @param registryConfig
     */
    @Override
    public void init(RegistryConfig registryConfig) {
        client = Client.builder()
                .endpoints(registryConfig.getAddress()) //服务器地址
                .connectTimeout(Duration.ofMillis(registryConfig.getTimeout())) //超时时间
                .build();
        kvClient = client.getKVClient();
    }

    /**
     * 服务创建，将服务元信息注册到etcd，并实现自动过期
     * @param serviceMetaInfo
     * @throws Exception
     */
    @Override
    public void register(ServiceMetaInfo serviceMetaInfo) throws Exception {
        // 创建 Lease 和 KV 客户端
        Lease leaseClient = client.getLeaseClient(); //获取租约客户端

        // 创建一个 30 秒的租约，提取租约ID
        long leaseId = leaseClient.grant(30).get().getID();

        // 设置要存储的键值对
        String registerKey = ETCD_ROOT_PATH + serviceMetaInfo.getServiceNodeKey(); //形成/rpc/服务名:版本号/主机:端口形式
        ByteSequence key = ByteSequence.from(registerKey, StandardCharsets.UTF_8); //将服务路径按UTF-8转换为二进制
        ByteSequence value = ByteSequence.from(JSONUtil.toJsonStr(serviceMetaInfo), StandardCharsets.UTF_8); //将服务元信息对象序列化为JSON，再转为二进制

        // 将键值对与租约关联起来，并设置过期时间
        PutOption putOption = PutOption.builder().withLeaseId(leaseId).build();
        kvClient.put(key, value, putOption).get(); //存储到Etcd，并等待操作完成
    }

    /**
     * 服务注销，删除键值对
     * @param serviceMetaInfo
     */
    @Override
    public void unRegister(ServiceMetaInfo serviceMetaInfo) {
        kvClient.delete(ByteSequence.from(ETCD_ROOT_PATH + serviceMetaInfo.getServiceNodeKey(), StandardCharsets.UTF_8));
    }

    /**
     * 服务发现
     * @param serviceKey 服务键名（服务名:版本号）
     * @return
     */
    @Override
    public List<ServiceMetaInfo> serviceDiscovery(String serviceKey) {
        // 前缀搜索，结尾一定要加 '/'
        String searchPrefix = ETCD_ROOT_PATH + serviceKey + "/";

        try {
            // 前缀查询，匹配所有以/rpc/{serviceKey}/开头的键
            GetOption getOption = GetOption.builder().isPrefix(true).build();
            List<KeyValue> keyValues = kvClient.get(
                            ByteSequence.from(searchPrefix, StandardCharsets.UTF_8),
                            getOption)
                    .get()
                    .getKvs();
            // 解析服务信息，将JSON解析为ServiceMetaInfo对象
            return keyValues.stream()
                    .map(keyValue -> {
                        String value = keyValue.getValue().toString(StandardCharsets.UTF_8);
                        return JSONUtil.toBean(value, ServiceMetaInfo.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("获取服务列表失败", e);
        }
    }

    /**
     * 注册中心销毁
     */
    @Override
    public void destroy() {
        System.out.println("当前节点下线");
        // 释放资源
        if (kvClient != null) {
            kvClient.close();
        }
        if (client != null) {
            client.close();
        }
    }
}
