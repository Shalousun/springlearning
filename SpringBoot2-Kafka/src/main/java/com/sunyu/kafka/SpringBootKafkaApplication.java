package com.sunyu.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yu on 2018/11/09.
 */
@EnableAsync
@SpringBootApplication
public class SpringBootKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApplication.class, args);
    }
}
