package com.sunyu.nacos.controller;

import com.power.common.model.CommonResult;
import com.power.common.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志测试
 * @author yu 2020/2/18.
 */
@RestController
public class LogController {

    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    /**
     * 日志打印
     *
     * @return
     */
    @GetMapping(value = "/")
    public CommonResult<String> testLog() {
        logger.info("info current time: {}", System.currentTimeMillis());
        logger.debug("current uuid: {}", UUIDUtil.getUuid());
        logger.error("error log");
        return CommonResult.ok().setResult("print log");
    }
}
