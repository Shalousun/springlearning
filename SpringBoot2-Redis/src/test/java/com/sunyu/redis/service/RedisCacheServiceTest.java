package com.sunyu.redis.service;

import com.sunyu.redis.cache.RedisCacheService;
import com.sunyu.redis.model.Province;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println(redisCacheService.get("hello"));
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
        System.out.println(redisCacheService.get("hg"));
    }
}
