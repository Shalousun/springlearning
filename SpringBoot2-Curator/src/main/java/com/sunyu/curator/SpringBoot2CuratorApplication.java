package com.sunyu.curator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2019/07/08.
 */
@SpringBootApplication
@MapperScan("com.sunyu.curator.dao")
public class SpringBoot2CuratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2CuratorApplication.class, args);
    }
}
