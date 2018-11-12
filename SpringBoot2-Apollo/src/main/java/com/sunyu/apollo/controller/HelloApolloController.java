package com.sunyu.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/12.
 */
@RestController
public class HelloApolloController {

    @Value("${spring.datasource.name}")//如果配置中心没有值，默认key为test的value值为test
    private String name;

    @GetMapping("/")
    public String getDbName(){
        return name;
    }
}
