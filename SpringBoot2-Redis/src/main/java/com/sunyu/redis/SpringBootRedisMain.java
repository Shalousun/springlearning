package com.sunyu.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author yu on 2018/11/08.
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.sunyu.redis.dao")
public class SpringBootRedisMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisMain.class, args);
    }
}
