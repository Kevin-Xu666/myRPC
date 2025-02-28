package com.xzs.myrpc.loadbalancer;

import com.xzs.myrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 随机负责均衡器
 */
public class RandomLoadBalancer implements LoadBalancer {

    private Random random = new Random();

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        int size = serviceMetaInfoList.size();
        if (size == 0) return null;
        if (size == 1) return serviceMetaInfoList.get(0);
        return serviceMetaInfoList.get(random.nextInt(size));
    }
}
