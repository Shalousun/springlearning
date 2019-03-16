package com.sunyu.redis.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * 默认是秒
     *
     * @param key
     * @param value
     * @param timeOut
     */
    void set(String key, Serializable value, Long timeOut);

    /**
     * 设置key和value的超时时间(设置成String返回类型,不然要设置成Void)
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
    <T> T get(String key,Class<T> tClass);

    /**
     * 获取值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 向一个Set集合中追加元素
     *
     * @param key
     * @param value
     */
    void setAdd(String key, Object... value);

    /**
     * @param key
     * @param <T>
     * @return
     */
    <T> Set<T> sGetAll(final String key, Class<T> tClass);

    /**
     *
     * @param key
     * @return
     */
    Set<String> sGetAll(final String key);
    /**
     * 检查是否是set集合中元素
     * @param key Redis key
     * @param member Set member
     * @return
     */
    Boolean isMember(String key, Object member);

    /**
     * 返回元素
     * @param key
     * @param tClass
     * @return
     */
    <V> List<V> sMembersList(String key, Class<V> tClass);

    /**
     * 从Set中删除元素
     * @param key key
     * @param member
     * @return
     */
    Long sRemove(String key,Object member);

    /**
     * @param key
     * @return
     */
    boolean hasKey(String key);
    /**
     * Atomically increments the current value by one.
     *
     * @param key
     * @return
     */
    Long increment(String key);

    /**
     * Atomically increments the current value by delta.
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key,long delta);

    /**
     * Set a timeout for object. After the timeout has expired,
     * the key will automatically be deleted.
     *
     * @param key     key
     * @param timeout timeout before object will be deleted
     * @param unit    timeout time unit
     * @return
     */
    Boolean expire(String key, final long timeout, final TimeUnit unit);

    /**
     * 让某个key在某一个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    Boolean expireAt(final String key, final long unixTime);


    /**
     * @param key
     * @param hKey
     * @param hValue
     */
    void hSet(String key, String hKey, Object hValue);
    /**
     * @param key
     * @param map
     */
    void hSet(String key, Map<String,Object> map);

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

    /**
     * @param key
     * @param hashKey
     * @param tClass
     * @return
     */
    <V> V hGet(String key, String hashKey,Class<V> tClass);

    /**
     * @param key
     * @param hashKey
     * @return
     */
    String hGet(String key, String hashKey);


    /**
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> hGetAllList(String key,Class<T> clazz);

    /**
     * 指定 list 从左入栈
     *
     * @param key
     * @param value
     * @return length of current stack
     */
    Long leftPush(final String key, Object value);

    /**
     * 指定 list 从左入栈多个value
     *
     * @param key
     * @param values
     * @return length of current stack
     */
    Long leftPushAll(final String key, Object... values);


    /**
     * list 从左出栈
     * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> T leftPop(final String key,Class<T> tClass);

    /**
     *
     * @param key
     * @return
     */
    String leftPop(final String key);

    /**
     * 指定 list 从右入栈
     *
     * @param key
     * @param value
     * @return length of current stack
     */
    Long rightPush(final String key, Object value);

    /**
     * 指定 list 从右入栈多个value
     *
     * @param key
     * @param values
     * @return length of current stack
     */
    Long rightPushAll(final String key, Object... values);

    /**
     * list 从右出栈
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @param <T>
     * @return
     */
    String  rightPop(final String key);

    /**
     *
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T rightPop(final String key,Class<T> tClass);

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return
     */
    <T> List<T> lRange(String key, long start, long end,Class<T> tClass);


    /**
     * 删除hash key
     *
     * @param key
     * @param hashKey
     */
    long hDel(String key, String hashKey);

    /**
     * 查询key的剩余时间(time to live)
     * @param key
     * @return
     */
    long ttl(String key);
    /**
     * 检查hash key
     * @param key key
     * @param hashKey hashKey
     * @return
     */
    boolean hasHKey(String key, String hashKey);


    /**
     * redis SETNX命令
     * 已存在返回true, 否则返回false
     *
     * @param key
     * @param value
     * @return
     */
    boolean setNx(String key, Object value);
}
