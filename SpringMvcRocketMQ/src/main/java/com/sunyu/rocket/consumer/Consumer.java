package com.sunyu.rocket.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 消息消费者
 */
public class Consumer implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        LOGGER.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size()+";msg:" + msgs);
        for (MessageExt msg : msgs) {
            try {
                System.out.println(">>>>"+new String(msg.getBody(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e.toString());
            }
        }
        // 有异常抛出来，不要全捕获了，这样保证不能消费的消息下次重推，每次重新消费间隔：10s,30s,1m,2m,3m
        // 如果没有异常会认为都成功消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
