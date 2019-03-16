package com.sunyu.redis.controller;

import com.alibaba.fastjson.JSON;
import com.power.common.model.CommonResult;
import com.sunyu.redis.cache.RedisCacheService;
import com.sunyu.redis.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yu 2019/2/13.
 */
@RestController
@RequestMapping("redis")
public class RedisCacheController {

    @Autowired
    RedisCacheService redisCacheService;

    @GetMapping("put")
    public CommonResult put(){
        Province province = new Province();
        province.setProvinceId("add");
        province.setCreateTime(new Date());
        //System.out.println(JSON.toJSONString(province));
        redisCacheService.set("provice",province);
        return CommonResult.ok();
    }

    @GetMapping("get")
    public CommonResult get(){
        Object obj = redisCacheService.get("provice",Province.class);
        return CommonResult.ok().setResult(obj);
    }
}
