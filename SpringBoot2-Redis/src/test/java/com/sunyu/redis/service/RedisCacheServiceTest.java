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


    @Test
    public void testSetString(){
        redisCacheService.set("hello","world");
    }

    @Test
    public void testGetString(){
        String str = redisCacheService.get("hello",String.class);
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
        Province province = redisCacheService.get("hg",Province.class);
        System.out.println(province.getProvinceName());
    }

    @Test
    public void testHashSet(){
        Province province = new Province();
        province.setProvinceId("47");
        province.setProvinceName("韩国省");
        Map<String,Object> map = new HashMap<>();
        map.put("name",province);
        redisCacheService.hset("test-hash",map);
        Map<String,Province> map1 = redisCacheService.hGetAll("test-hash",Province.class);
        System.out.println(map1.get("name").getProvinceName());
    }

    @Test
    public void testHashString(){
        Map<String,Object> map = new HashMap<>();
        //map.put("name","yusun");
        map.put("int",30);
        redisCacheService.hset("test-hash-str",map);
        Map<String,Integer> map1 = redisCacheService.hGetAll("test-hash-str",Integer.class);
       // System.out.println(map1.get("name"));
        System.out.println(map1.get("int"));
    }
}
