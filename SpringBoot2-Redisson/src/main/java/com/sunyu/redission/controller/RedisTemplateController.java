package com.sunyu.redission.controller;

import com.power.common.model.CommonResult;
import com.sunyu.redission.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关于redis template的测试
 * @author yu 2018/11/5.
 */
@RestController
@RequestMapping("redisTpl")
public class RedisTemplateController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private String key = "my-key";

    @GetMapping("get")
    public String getVal(){
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("set")
    public CommonResult setVal(){
        redisTemplate.opsForValue().set(key,"hello");
        return ResultUtil.success("");
    }
}
