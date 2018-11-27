package com.test.rocketmq.consumer.runner;

import com.test.rocketmq.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yu 2018/11/27.
 */
@Component
public class ConsumerRunner implements CommandLineRunner {

    @Resource
    private MessageListener messageListener;

    @Override
    public void run(String... args) throws Exception {
        //01默认的消息消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");
        //02注册
        consumer.setNamesrvAddr("192.168.237.131:9876");
        //03设置获取原则
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //04订阅
        consumer.subscribe("TopicTransaction","*");

        consumer.setMessageListener(messageListener);

        consumer.start();
    }
}
