package com.sunyu.nacos.config;


import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class LoggingLevelRefresher {

    private final static Logger log = LoggerFactory.getLogger(LoggingLevelRefresher.class);

    @NacosInjected
    private ConfigService configService;

    @NacosValue(value = "${log.logger:com}",autoRefreshed = true)
    private String loggerName;
    @NacosValue(value = "${log.level}",autoRefreshed = true)
    private String loggerLevel;

    @Value("${nacos.config.group}")
    private String group;

    @Value("${nacos.config.data-id}")
    private String dataId;

    @Resource
    private LoggingSystem loggingSystem;
    @PostConstruct
    public void init() {
        try {
            onMessage();
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
    public void onMessage() throws NacosException {
        configService.addListener(dataId, group, new AbstractListener() {
            public void receiveConfigInfo(String value) {
                log.info("received changed value = {}",value);
                log.info("changed {} log level to:{}", loggerName, loggerLevel);
                LogLevel logLevel = LogLevel.valueOf(loggerLevel.toUpperCase());
                loggingSystem.setLogLevel(loggerName,logLevel);
            }
        });
    }

}
