package com.boco.sunyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  13:09:24.
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringBootMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApplication.class, args);
    }

}
