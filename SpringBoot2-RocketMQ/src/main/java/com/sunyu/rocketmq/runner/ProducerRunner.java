package com.sunyu.rocketmq.runner;

import com.sunyu.rocketmq.model.OrderPaidEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yu 2018/11/5.
 */
@Component
public class ProducerRunner implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("T_001");
        orderPaidEvent.setPaidMoney(new BigDecimal("88.00"));
        rocketMQTemplate.convertAndSend("test-topic-2", orderPaidEvent);
    }

}
