package com.sunyu.redission.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * @author yu 2018/11/5.
 */
@Configuration
public class RedissonConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedissonConfig.class);

    @Bean
    public Config getConfig(Environment env) {
        Config config = new Config();
        String nodes = env.getProperty("spring.redis.cluster.nodes");
        if (StringUtils.isNotEmpty(nodes)) {
            logger.info("RedissonClient is use cluster redis server.");
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            String password = env.getProperty("spring.redis.password");
            if (StringUtils.isNotEmpty(password)) {
               clusterServersConfig.setPassword(password);
            }
            String[] nodesList = nodes.split(",");
            for(int i=0;i<nodesList.length;i++){
                nodesList[i] = "redis://"+nodesList[i];
            }
            clusterServersConfig.addNodeAddress(nodesList);
        } else {
            logger.info("RedissonClient is use single redis server.");
            String singleServer = "redis://" + env.getProperty("spring.redis.host") + ":" + env.getProperty("spring.redis.port");
            config.useSingleServer().setAddress(singleServer);
        }
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(Config config) throws IOException {
        logger.info("create RedissonClient, config is : {}", config.toJSON());
        return Redisson.create(config);
    }
}
