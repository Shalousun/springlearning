package com.sunyu.curator.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author yu 2019/7/8.
 */
public abstract class AbstractZookeeperLock<T> {

    private static final int TIME_OUT = 5;

    public abstract String getLockPath();

    public abstract T execute();

    public int getTimeout() {
        return TIME_OUT;
    }

    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

}
