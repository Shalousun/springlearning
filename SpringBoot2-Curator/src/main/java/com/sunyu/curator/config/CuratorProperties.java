package com.sunyu.curator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yu 2019/7/8.
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class CuratorProperties {
    private int retryCount;
    private int elapsedTimeMs;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}


