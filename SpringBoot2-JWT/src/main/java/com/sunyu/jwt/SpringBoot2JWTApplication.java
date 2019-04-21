package com.sunyu.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2019/04/21.
 */
@SpringBootApplication
@MapperScan("com.sunyu.jwt.dao")
public class SpringBoot2JWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2JWTApplication.class, args);
    }
}
