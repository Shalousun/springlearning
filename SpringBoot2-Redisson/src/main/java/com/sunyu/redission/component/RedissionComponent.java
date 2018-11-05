package com.sunyu.redission.component;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yu 2018/11/5.
 */
@Component
public class RedissionComponent implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RedissionComponent.class);
    @Autowired
    private RedissonClient redissonClient;

    public static Redisson redisson;

    /**
     * 获取默认锁
     */
    public static RLock acquireLock(String lockName) {
        RLock fairLock = redisson.getLock(lockName);
        //unlock automatically after 2 minutes of hold
        fairLock.lock(2, TimeUnit.MINUTES);
        logger.info("get lock success!");
        return fairLock;
    }

    /**
     * 释放默认锁
     */
    public static void realeaseLock(RLock fairLock) {
        fairLock.unlock();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        redisson = (Redisson) redissonClient;
        logger.info(redisson.getConfig().toJSON());
    }

    /**
     * 存入分布式set对象中
     *
     * @param name
     * @param value
     * @return
     */
    public static RSet<String> setSet(String name, String value) {
        RSet<String> set = redisson.getSet(name);
        set.add(value);
        return set;
    }

    /**
     * 检查分布式对象是否存在
     *
     * @param name
     * @return
     */
    public static Boolean existSet(String name) {
        RSet<String> set = redisson.getSet(name);
        if (null!= set && !set.isEmpty()) {
            return true;
        }
        return false;
    }

}
