package com.test.rocketmq.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2018/11/27.
 */
@SpringBootApplication
@MapperScan("com.test.rocketmq.consumer.dao")
public class RocketMqConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumerApp.class, args);
    }
}
