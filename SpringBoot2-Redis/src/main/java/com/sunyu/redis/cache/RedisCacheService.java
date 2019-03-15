package com.sunyu.redis.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yu 2018/8/30.
 */
public interface RedisCacheService {


    /**
     * 设置缓存到redis中(无过期)
     *
     * @param key
     * @param value
     */
    void set(String key, Serializable value);

    /**
     * 存活时间分钟
     *
     * @param key
     * @param value
     * @param timeOut
     */
    void set(String key, Serializable value, Long timeOut);

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param timeOut  存活时间
     * @param timeUnit 单位
     */
    void set(String key, Serializable value, Long timeOut, TimeUnit timeUnit);

    /**
     * 删除key
     *
     * @param key
     */
    void delete(String key);

    /**
     * 从redis获取
     * @param key
     * @return
     */
    <T> T get(String key);

    /**
     * 向一个Set集合中追加元素
     *
     * @param key
     * @param value
     */
    void setAdd(String key, Object... value);

    /**
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     *
     * @param key
     * @param delta
     * @return
     */
    long increment(String key, long delta);


    /**
     * @param key
     * @param map
     */
    void hSet(String key, Map<String, String> map);

    void hset(String key, Map<String, Object> map);


    /**
     * 获取hash类型的集合
     *
     * @param key
     * @return
     */
    <K, V> Map<K, V> hGetAll(String key,Class<V> vClass);

    /**
     *
     * @param key
     * @return
     */
    Map<String,String> hGetAll(String key);
}
