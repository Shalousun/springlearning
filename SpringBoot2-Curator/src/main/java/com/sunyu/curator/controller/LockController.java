package com.sunyu.curator.controller;

import com.power.common.model.CommonResult;
import com.sunyu.curator.enums.ErrorCode;
import com.sunyu.curator.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2019/7/8.
 */
@RestController
public class LockController {
    @Autowired
    private DistributedLock distributedLock;

    private final static String PATH = "test";

    @GetMapping("/lock1")
    public CommonResult<Boolean> getLock1() {
        Boolean flag;
        distributedLock.acquireDistributedLock(PATH);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLock.releaseDistributedLock(PATH);
        }
        flag = distributedLock.releaseDistributedLock(PATH);
        return flag?CommonResult.ok(ErrorCode.LOCKED_SUCCESS):CommonResult.fail();
    }

    @GetMapping("/lock2")
    public CommonResult getLock2() {
        Boolean flag;
        distributedLock.acquireDistributedLock(PATH);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLock.releaseDistributedLock(PATH);
        }
        flag = distributedLock.releaseDistributedLock(PATH);
        return flag?CommonResult.ok(ErrorCode.LOCKED_SUCCESS):CommonResult.fail();
    }
}
