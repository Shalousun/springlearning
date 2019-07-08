package com.sunyu.curator.controller;

import com.power.common.model.CommonResult;
import com.sunyu.curator.enums.ErrorCode;
import com.sunyu.curator.lock.AbstractZookeeperLock;
import com.sunyu.curator.lock.SharedLocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yu 2019/7/8.
 */
@Controller
public class SharedLocksController {

    private static final java.lang.String LOCK_PATH = "test_";
    @Autowired
    private SharedLocks sharedLocks;

    @GetMapping("sharedLock")
    public CommonResult testLock() {

        String lockPath = "123";
        String result = sharedLocks.lock(new AbstractZookeeperLock<String>() {
            @Override
            public String getLockPath() {
                return LOCK_PATH + lockPath;
            }

            @Override
            public String execute() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return this.getLockPath();
            }
        });

        if (null == result) {
            return CommonResult.fail(ErrorCode.LOCKED_ERROR);
        }
        return CommonResult.ok(ErrorCode.LOCKED_SUCCESS);
    }
}
