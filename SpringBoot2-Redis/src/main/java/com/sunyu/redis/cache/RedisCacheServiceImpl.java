package com.sunyu.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yu 2018/11/08.
 */
@Slf4j
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Override
    public void set(String key, Serializable value) {
        log.debug("redis set key: {}",key);
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void set(String key,Serializable value, Long timeOut) {
        log.debug("redis set key: {}",key);
        this.set(key, value, timeOut, TimeUnit.MINUTES);
    }

    @Override
    public void set(String key, Serializable value, Long timeOut, TimeUnit timeUnit) {
        log.debug("redis set key: {}",key);
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    @Override
    public void delete(String key) {
        log.debug("redis delete key: {}",key);
        redisTemplate.delete(key);
    }

    @Override
    public Object get(String key) {
        log.debug("redis get key: {}",key);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<? extends Serializable> requiredType) {
        log.debug("redis get key: {}",key);
        Serializable val = redisTemplate.opsForValue().get(key);
        if(val == null){
            return null;
        }
        return ((T)val);
    }

    @Override
    public void setAdd(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
