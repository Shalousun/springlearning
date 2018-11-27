package com.sunyu.rocketmq.runner;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

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
            msg = new Message("mytx-topic", "hello-word".getBytes(RemotingHelper.DEFAULT_CHARSET));
            rocketMQTemplate.sendMessageInTransaction(txProducerGroup, msg, null);
        } catch (UnsupportedEncodingException | MQClientException e) {
            e.printStackTrace();
        }
    }
}

