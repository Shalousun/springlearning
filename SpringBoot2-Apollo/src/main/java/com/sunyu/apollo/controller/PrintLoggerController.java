package com.sunyu.apollo.controller;

import com.power.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/14.
 */
@Slf4j
@RestController
public class PrintLoggerController {

    @GetMapping("log")
    public CommonResult printLog(){
        log.info("我是info级别日志");
        log.error("我是error级别日志");
        log.warn("我是warn级别日志");
        log.debug("我是debug级别日志");
        return CommonResult.ok();
    }
}
