package com.sunyu.redission.controller;

import com.power.common.model.CommonResult;
import com.sunyu.redission.component.RedissionComponent;
import com.sunyu.redission.enums.ErrorCodeEnum;
import com.sunyu.redission.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/5.
 */
@RestController
@RequestMapping("redissonComponent")
public class RedissonComponentController {


    @GetMapping("put")
    public CommonResult rset(){
        RedissionComponent.setSet("hello-component","word");

        return ResultUtil.success("");
    }

    @GetMapping("get")
    public CommonResult rsetGet(){
        if (RedissionComponent.existSet("hello-component")) {
            return ResultUtil.success("");
        }else {
            return ResultUtil.error(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }
}
