package com.sunyu.redission.controller;

import com.power.common.model.CommonResult;
import com.sunyu.redission.component.RedissionComponent;
import com.sunyu.redission.enums.ErrorCodeEnum;
import com.sunyu.redission.util.ResultUtil;
import org.redisson.api.RLock;
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

    @GetMapping("lock")
    public CommonResult testDistributedRedisLock() throws Exception{
        for (int i = 0; i < 300; i++) {
          Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                  String key = "myLock";
                  try {
                      RLock lock = RedissionComponent.acquireLock(key);
                      Thread.sleep(5); //获得锁之后可以进行相应的处理
                      System.err.println("======do process business======");
                      RedissionComponent.realeaseLock(lock);
                      System.err.println("=============================");
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          });
          thread.start();
          thread.join();
        }
        return ResultUtil.success("null");
    }
}
