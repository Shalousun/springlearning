package com.sunyu.rocketmq.runner;

import com.sunyu.rocketmq.model.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author yu 2018/11/5.
 */
@Slf4j
@Component
public class ProducerRunner implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) {
        log.debug("start write msg to rocketmq");
        rocketMQTemplate.convertAndSend("test-topic-1:tag0", "Hello, World!");
        rocketMQTemplate.send("test-topic-1:tag0", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("T_001");
        orderPaidEvent.setPaidMoney(new BigDecimal("99.00"));
        rocketMQTemplate.convertAndSend("test-topic-2:tag0", orderPaidEvent);
    }

}
