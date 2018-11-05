package com.sunyu.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2018/11/05.
 */
@SpringBootApplication
@MapperScan("com.sunyu.rocketmq.dao")
public class SpringBootMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApplication.class, args);
    }
}
