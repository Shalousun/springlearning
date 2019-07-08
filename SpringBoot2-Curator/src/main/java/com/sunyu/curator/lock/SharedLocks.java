package com.sunyu.curator.lock;

import com.power.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yu 2019/7/8.
 */
@Slf4j
@Service
public class SharedLocks {
    private final static String ROOT_PATH_LOCK = "rootlock";
    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 分布式锁测试
     * @return
     * @throws Exception
     */
    public <T> T lock(AbstractZookeeperLock<T> mutex) {
        String path = ROOT_PATH_LOCK + mutex.getLockPath();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);
        boolean success = false;
        try {
            try {
                success = lock.acquire(mutex.getTimeout(), mutex.getTimeUnit());
            } catch (Exception e) {
                throw new RuntimeException("obtain lock error " + e.getMessage() + ", path " + path);
            }
            if (success) {
                return (T) mutex.execute();
            } else {
                return null;
            }
        } finally {
            try {
                if (success){
                    lock.release();
                }
            } catch (Exception e) {
                log.error("release lock error {}, path {}", e.getMessage(), path);
            }
        }
    }

}
