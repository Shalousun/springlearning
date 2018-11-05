package com.sunyu.redission.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu 2018/11/5.
 */
@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient create(){
        return Redisson.create();
    }
}
