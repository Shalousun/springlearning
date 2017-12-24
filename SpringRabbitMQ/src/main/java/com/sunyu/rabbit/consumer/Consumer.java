package com.sunyu.rabbit.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * rabbit mq消费者
 * Created by yu on 2017/4/29.
 */
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("消息消费者 = " + message.toString());
    }
}
