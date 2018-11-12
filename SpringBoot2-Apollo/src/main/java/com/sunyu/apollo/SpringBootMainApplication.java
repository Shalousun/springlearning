package com.sunyu.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.sunyu.apollo.util.TestJavaConfigBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu on 2018/11/12.
 */
@SpringBootApplication
@Configuration
@EnableApolloConfig
@MapperScan("com.sunyu.apollo.dao")
public class SpringBootMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApplication.class, args);
    }


    @Bean
    public TestJavaConfigBean javaConfigBean() {
        return new TestJavaConfigBean();
    }
}
