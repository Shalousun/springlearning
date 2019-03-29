package com.sunyu.redis.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yu 2018/11/08.
 */
@Slf4j
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Serializable value) {
        logger.debug("redis set key: {}", key);
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Serializable value, Long timeOut) {
        logger.debug("redis set key: {}", key);
        this.set(key, value, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Serializable value, Long timeOut, TimeUnit timeUnit) {
        logger.debug("setEX redis key: {} value: {} timeOut: {}", key, JSON.toJSONString(value), timeOut, timeUnit);
        if (StringUtils.isNotEmpty(key)) {
            redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
        }
    }

    @Override
    public void delete(String key) {
        logger.debug("redis delete key: {}", key);
        redisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key, Class<T> tClass) {
        logger.debug("Get from Redis key is {} ", key);
        Object val = redisTemplate.opsForValue().get(key);
        if (val == null) {
            return null;
        }
        if (isPrimaryClass(tClass)) {
            return (T) val;
        }
        return JSON.parseObject(val.toString(), tClass);
    }


    @Override
    public String get(String key) {
        return get(key, String.class);
    }

    @Override
    public <T> T getAndSet(String key, Object value, Class<T> tClass) {
        logger.debug("Get And Set from Redis key is {} value", key);
        Object val = redisTemplate.opsForValue().getAndSet(key,value);
        if (val == null) {
            return null;
        }
        if (isPrimaryClass(tClass)) {
            return (T) val;
        }
        return JSON.parseObject(val.toString(), tClass);
    }

    @Override
    public <T> List<T> multiGet(Collection<String> keys, Class<T> tClass) {
        List<Object> list = redisTemplate.opsForValue().multiGet(keys);
        if(isPrimaryClass(tClass)){
            return (List<T>)list;
        }else {
            List<T> list1 = new ArrayList<>(list.size());
            list.forEach(s -> list1.add(JSON.parseObject(s.toString(), tClass)));
            return list1;
        }
    }

    @Override
    public void setAdd(String key, Object... value) {
        logger.debug("Redis Set Add key: {} value: {}", key, JSON.toJSONString(value));
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public <T> Set<T> sGetAll(String key, Class<T> tClass) {
        logger.debug("Redis Set get key: {}", key);
        Set<T> setT = redisTemplate.opsForSet().members(key);
        if (isPrimaryClass(tClass)) {
            return setT;
        } else {
            Set<T> setObj = new HashSet<>(setT.size());
            setT.forEach(v -> {
                setObj.add(JSON.parseObject(v.toString(), tClass));
            });
            return setObj;
        }
    }

    @Override
    public Set<String> sGetAll(String key) {
        return sGetAll(key, String.class);
    }

    @Override
    public Boolean isMember(String key, Object member) {
        logger.debug("Redis[Set is member] key:{} member:{}", key, JSON.toJSONString(member));
        return redisTemplate.opsForSet().isMember(key, member);
    }


    @Override
    public <V> List<V> sMembersList(String key, Class<V> tClass) {
        Set<V> set = sGetAll(key, tClass);
        return set.stream().collect(Collectors.toList());
    }

    @Override
    public Long sRemove(String key, Object member) {
        logger.debug("Redis Set delete key:{} member: {}", key, JSON.toJSONString(member));
        return redisTemplate.opsForSet().remove(key, member);
    }

    @Override
    public void hSet(String key, String hKey, Object hValue) {
        logger.debug("hSet redis key: {} hKey: {} hValue: {}", key, hKey, JSON.toJSONString(hValue));
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(hKey)) {
            redisTemplate.boundHashOps(key).put(hKey, hValue);
        }
    }

    @Override
    public void hSet(String key, Map<String, Object> map) {
        logger.debug("hSet redis key: {} value: {}", key, JSON.toJSONString(map));
        if (StringUtils.isNotEmpty(key) && null != map) {
            redisTemplate.boundHashOps(key).putAll(map);
        }
    }

    @Override
    public <K, V> Map<K, V> hGetAll(String key, Class<V> vClass) {
        logger.debug("hGetAll redis key: {} ", key);
        Map<K, V> map = redisTemplate.opsForHash().entries(key);
        if (isPrimaryClass(vClass)) {
            return map;
        }
        Map<K, V> kvMap = new HashMap<>(map.size());
        map.forEach((k, v) -> {
            kvMap.put(k, JSON.parseObject(v.toString(), vClass));
        });
        return kvMap;
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return hGetAll(key, String.class);
    }

    @Override
    public <V> V hGet(String key, String hashKey, Class<V> tClass) {
        logger.debug("hGet key: {} hashKey: {}", key, hashKey);
        BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(key);
        Object value = boundHashOperations.get(hashKey);
        if (isPrimaryClass(tClass)) {
            return (V) value;
        } else {
            return JSON.parseObject(boundHashOperations.get(hashKey).toString(), tClass);
        }
    }

    @Override
    public String hGet(String key, String hashKey) {
        return hGet(key, hashKey, String.class);
    }

    @Override
    public <T> List<T> hGetAllList(String key, Class<T> clazz) {
        Map<String, T> map = hGetAll(key, clazz);
        if (null != map) {
            return map.values().stream().collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }


    @Override
    public String leftPop(String key) {
        return leftPop(key, String.class);
    }

    @Override
    public <T> T leftPop(String key, Class<T> tClass) {
        logger.debug("Redis left pop key: {}", key);
        Object val = redisTemplate.opsForList().leftPop(key);
        if (val == null) {
            return null;
        }

        if (isPrimaryClass(tClass)) {
            return (T) val;
        } else {
            return JSON.parseObject(val.toString(), tClass);
        }
    }

    @Override
    public Long leftPush(String key, Object value) {
        logger.debug("LeftPush key: {} value: {}", key, JSON.toJSONString(value));
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long leftPushAll(String key, Object... values) {
        logger.debug("LeftPushAll key: {} value: {}", key, JSON.toJSONString(values));
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long rightPush(String key, Object value) {
        logger.debug("RightPush key: {} value: {}", key, JSON.toJSONString(value));
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long rightPushAll(String key, Object... values) {
        logger.debug("RightPushAll key: {} value: {}", key, JSON.toJSONString(values));
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public String rightPop(String key) {
        return rightPop(key, String.class);
    }

    @Override
    public <T> T rightPop(String key, Class<T> tClass) {
        logger.debug("RightPop from Redis key is {} ", key);
        Object val = redisTemplate.opsForList().rightPop(key);
        if (val == null) {
            return null;
        }

        if (isPrimaryClass(tClass)) {
            return (T) val;
        } else {
            return JSON.parseObject(val.toString(), tClass);
        }
    }

    @Override
    public <T> List<T> lRange(String key, long start, long end, Class<T> tClass) {
        logger.debug("lRange key: {} start: {} end: {}", key, start, end);
        List<T> list = redisTemplate.opsForList().range(key, start, end);
        if (isPrimaryClass(tClass)) {
            return list;
        } else {
            List<T> list1 = new ArrayList<>(list.size());
            list.forEach(s -> list1.add(JSON.parseObject(s.toString(), tClass)));
            return list1;
        }
    }

    @Override
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public long hDel(String key, String hashKey) {
        logger.debug("hDel key: {} hashKey: {}", key, hashKey);
        return redisTemplate.boundHashOps(key).delete(hashKey);
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public boolean hasHKey(String key, String hashKey) {
        return redisTemplate.boundHashOps(key).hasKey(hashKey);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long increment(String key) {
        logger.debug("increment key: {} ,delta: 1", key);
        return redisTemplate.opsForValue().increment(key, 1L);
    }

    @Override
    public Long increment(String key, long delta) {
        logger.debug("increment key: {} ,delta: {}", key, delta);
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        logger.debug("expire key: {} timeout: {} unit: {}", key, timeout, unit);
        return redisTemplate.expire(key, timeout, unit);
    }


    @Override
    public Boolean expireAt(String key, long unixTime) {
        logger.debug("expireAt key: {} atTime: {}", key, unixTime);
        return redisTemplate.expireAt(key, new Date(unixTime));
    }

    @Override
    public boolean setNx(String key, Object value) {
        logger.debug("setNx redis key: {} value: {}", key, JSON.toJSONString(value));
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    private boolean isPrimaryClass(Class vClass) {
        if (vClass == String.class || vClass == Integer.class || vClass == Long.class ||
                vClass == Boolean.class || vClass == Double.class || vClass == Float.class) {
            return true;
        } else {
            return false;
        }
    }
}
