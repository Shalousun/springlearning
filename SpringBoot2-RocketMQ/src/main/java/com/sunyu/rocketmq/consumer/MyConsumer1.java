package com.sunyu.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 *  test-topic-1 的消费测试已经移动到consumer模块
 * @author yu 2018/11/6.
 */
@Slf4j
//@Service
//@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer1 implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.debug("rocket string msg: {}",s);
    }
}
