package com.sunyu.redis.service;

import com.sunyu.redis.cache.RedisCacheService;
import com.sunyu.redis.model.Province;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        String key = "test-hash";
        redisCacheService.hSet("test-hash",map);
        Map<String,Province> map1 = redisCacheService.hGetAll("test-hash",Province.class);
        System.out.println(map1.get("name").getProvinceName());
        List<Province> provinceList = redisCacheService.hGetAllList(key,Province.class);
        provinceList.forEach(s-> System.out.println(s.getProvinceName()));

    }

    @Test
    public void testHashString(){
        String key = "test-hash-str";
        String hashKey = "hash-key";
        Map<String,Object> map = new HashMap<>();
        //map.put("name","yusun");
        map.put(hashKey,30);
        redisCacheService.hSet("test-hash-str",map);
        Map<String,Integer> map1 = redisCacheService.hGetAll("test-hash-str",Integer.class);
       // System.out.println(map1.get("name"));
        System.out.println(map1.get(hashKey));
        int a = redisCacheService.hGet(key,hashKey,Integer.class);
        System.out.println(a);
    }

    @Test
    public void testSetData(){
        String key = "set-test";
//        redisCacheService.setAdd(key, "hello","world");
//        Set<String> stringSet = redisCacheService.sGetAll(key,String.class);
//        System.out.println("================================");
//        stringSet.forEach(s -> System.out.println(s));

        Province province = new Province();
        province.setProvinceId("47");
        province.setProvinceName("韩国省");

        redisCacheService.setAdd(key,province);
        Set<Province> pro = redisCacheService.sGetAll(key,Province.class);
        System.out.println("================================");
        pro.forEach(s -> System.out.println(s.getProvinceName()));

        List<Province> provinceList = redisCacheService.hGetAllList(key,Province.class);
    }

    @Test
    public void testLeftPush(){
        String key = "left-key";
        Province province = new Province();
        province.setProvinceId("47");
        province.setProvinceName("韩国省");
        //redisCacheService.leftPushAll(key,province,province);
        Province val = redisCacheService.leftPop(key,Province.class);
        System.out.println(val.getProvinceName());
    }
}
