package com.sunyu.rocketmq.consumer;

import com.sunyu.rocketmq.model.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * rocketmq消费者
 * @author yu 2018/11/6.
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.debug("rocket object msg: {}",orderPaidEvent.toString());
    }
}
