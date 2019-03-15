package com.sunyu.redis.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yu 2018/11/08.
 */
@Slf4j
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Serializable value) {
        log.debug("redis set key: {}", key);
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Serializable value, Long timeOut) {
        log.debug("redis set key: {}", key);
        this.set(key, value, timeOut, TimeUnit.MINUTES);
    }

    @Override
    public void set(String key, Serializable value, Long timeOut, TimeUnit timeUnit) {
        log.debug("redis set key: {}", key);
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    @Override
    public void delete(String key) {
        log.debug("redis delete key: {}", key);
        redisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key) {
        log.debug("Get from Redis key is {} ", key);
        Object val = redisTemplate.opsForValue().get(key);
        if (val == null) {
            return null;
        }
        return (T) val;
    }


    @Override
    public void setAdd(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public void hSet(String key, Map<String, String> map) {
        if (StringUtils.isNotEmpty(key) && null != map) {
            redisTemplate.boundHashOps(key).putAll(map);
        }
    }

    @Override
    public void hset(String key, Map<String, Object> map) {
        if (StringUtils.isNotEmpty(key) && null != map) {
            redisTemplate.boundHashOps(key).putAll(map);
        }
    }

    @Override
    public <K, V> Map<K, V> hGetAll(String key, Class<V> vClass) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        if (vClass == String.class) {
            return (Map<K, V>) map;
        }
        Map<K, V> kvMap = new HashMap<>();
        map.forEach((k, v) -> {
            kvMap.put((K) k, JSON.parseObject(v.toString(), vClass));
        });
        return kvMap;
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return hGetAll(key,String.class);
    }
}
