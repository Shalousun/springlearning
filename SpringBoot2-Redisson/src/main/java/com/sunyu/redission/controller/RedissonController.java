package com.sunyu.redission.controller;

import com.power.common.model.CommonResult;
import com.sunyu.redission.enums.ErrorCodeEnum;
import com.sunyu.redission.util.ResultUtil;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redisson Demo
 * @author yu 2018/11/5.
 */
@RestController
@RequestMapping("redission")
public class RedissonController {

    @Autowired
    private RedissonClient redisson;

    /**
     * long自增
     * @return
     */
    @GetMapping("long")
    public CommonResult atomicLong() {
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        atomicLong.incrementAndGet();
        return ResultUtil.success(atomicLong.get());
    }

    /**
     * double则增
     * @return
     */
    @GetMapping("double")
    public CommonResult atomicDouble() {
        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
        atomicDouble.set(2.81);
        atomicDouble.addAndGet(4.11);
        return ResultUtil.success(atomicDouble.get());
    }

    @GetMapping("rateLimiter")
    public CommonResult rateLimiter(){
        RRateLimiter rateLimiter = redisson.getRateLimiter("myRateLimiter");
        boolean flag = rateLimiter.tryAcquire(4);
        if(flag){
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(ErrorCodeEnum.RATE_LIMIT_ERROR);
        }
    }
}
