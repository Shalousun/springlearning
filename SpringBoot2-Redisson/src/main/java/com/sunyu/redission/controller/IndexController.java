package com.sunyu.redission.controller;

import com.sunyu.redission.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/5.
 */
@RestController
public class IndexController {

    @RateLimit
    @GetMapping
    public String index(){
        return "hello";
    }
}
