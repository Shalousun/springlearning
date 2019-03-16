package com.sunyu.redis.service;

import com.sunyu.redis.cache.RedisCacheService;
import com.sunyu.redis.model.Province;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu 2018/11/8.
 */
public class RedisCacheServiceTest extends ServiceBaseTest {

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testSetString(){
        redisTemplate.opsForValue().set("hello","world");
       // redisCacheService.set("hello","world");
    }

    @Test
    public void testGetString(){
        String str = redisCacheService.get("hello");
        System.out.println(str);
    }

    @Test
    public void testSetObject(){
        Province province = new Province();
        province.setProvinceId("47");
        province.setProvinceName("韩国省");
        redisCacheService.set("hg",province);
    }

    @Test
    public void testGetObject(){
        Province province = redisCacheService.get("hg");
        System.out.println(province.getProvinceName());
    }

    @Test
    public void testHashSet(){
        Province province = new Province();
        province.setProvinceId("47");
        province.setProvinceName("韩国省");
        Map<String,Object> map = new HashMap<>();
        map.put("name","yusun");
        redisCacheService.hset("test-hash",map);
        Map<String,String> map1 = redisCacheService.hGetAll("test-hash",String.class);
        System.out.println(map1.get("name"));
    }
}
