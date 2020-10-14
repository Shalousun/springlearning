package com.demo.dubbo.balance;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * dubbo 灰度发布负载均衡器
 * @author yu 2019/9/19.
 */
@Slf4j
@Component
public class GrayLoadBalance extends AbstractLoadBalance {

    public static final String NAME = "gray";

    public GrayLoadBalance() {
    }

    /**
     * 通过用户id来选择灰度服务
     * @param invokers
     * @param url
     * @param invocation
     * @param <T>
     * @return
     */
    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        List<Invoker<T>> list = new ArrayList<>();
        for (Invoker invoker : invokers) {
            list.add(invoker);
        }
        Map<String, String> map = invocation.getAttachments();
        String userId = map.get("userId");
        log.info("userId={}",userId);
        Iterator<Invoker<T>> iterator = list.iterator();
        String grayUserIds = url.getParameter("grayUserids", "");
        log.info("grayUserids={}",grayUserIds);
        String[] arrays = grayUserIds.split(",");
        while (iterator.hasNext()) {
            Invoker<T> invoker = iterator.next();
            String providerStatus = invoker.getUrl().getParameter("status", "prod");
            log.info("provider status ={}",providerStatus);
            if (Objects.equals(providerStatus, NAME)) {
                if (Arrays.asList(arrays).contains(userId)) {
                    return invoker;
                } else {
                    iterator.remove();
                }
            }
        }
        return this.randomSelect(list, url, invocation);
    }


    /**
     * 重写了一遍随机负载策略
     *
     * @param invokers
     * @param url
     * @param invocation
     * @param <T>
     * @return
     */
    private <T> Invoker<T> randomSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        int length = invokers.size();
        boolean sameWeight = true;
        int[] weights = new int[length];
        int firstWeight = this.getWeight(invokers.get(0), invocation);
        weights[0] = firstWeight;
        int totalWeight = firstWeight;

        int offset;
        int i;
        for (offset = 1; offset < length; ++offset) {
            i = this.getWeight(invokers.get(offset), invocation);
            weights[offset] = i;
            totalWeight += i;
            if (sameWeight && i != firstWeight) {
                sameWeight = false;
            }
        }

        if (totalWeight > 0 && !sameWeight) {
            offset = ThreadLocalRandom.current().nextInt(totalWeight);

            for (i = 0; i < length; ++i) {
                offset -= weights[i];
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
        }
        return invokers.get(ThreadLocalRandom.current().nextInt(length));
    }
}
