package com.sunyu.redis.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 消息生产者
 */
@Component
public class SendMessage {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

}
