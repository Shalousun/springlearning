package com.sunyu.k8s;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2018/12/31.
 */
@SpringBootApplication
@MapperScan("com.sunyu.k8s.dao")
public class SpringBootKubernetes {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKubernetes.class, args);
    }
}
