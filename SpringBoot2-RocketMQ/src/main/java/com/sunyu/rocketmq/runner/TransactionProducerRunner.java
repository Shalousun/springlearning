package com.sunyu.rocketmq.runner;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * 消息事务demo
 *
 * @author yu 2018/11/27.
 */
public class TransactionProducerRunner implements CommandLineRunner {

    private final String txProducerGroup = "mytx";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) {
        Message msg = null;
        try {
            msg = MessageBuilder.withPayload("hello world").build();
            //new Message("mytx-topic", "hello-word".getBytes(RemotingHelper.DEFAULT_CHARSET));
            rocketMQTemplate.sendMessageInTransaction(txProducerGroup,"mytx-topic", msg, null);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

