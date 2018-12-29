package com.sunyu.prometheus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2018/12/29.
 */
@SpringBootApplication
@MapperScan("com.sunyu.prometheus.dao")
public class SpringBootPrometheus {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPrometheus.class, args);
    }
}
