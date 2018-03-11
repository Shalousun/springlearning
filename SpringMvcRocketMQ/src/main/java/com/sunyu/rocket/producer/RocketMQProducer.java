package com.sunyu.rocket.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.util.List;

@Service
public class RocketMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQProducer.class);
    /**
     * 生产者的组名
     */
    @Value("${rocketmq.producerGroup}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    /**
     * 发送单条消息
     * @param topic 主题
     * @param tags 标签
     * @param body 消息体
     */
    public void sendMsg(String topic,String tags,String body){

        System.out.println(namesrvAddr);
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        try{
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            Message message = new Message(topic,tags,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message);
            LOGGER.debug("发送响应MsgId:{}，发送状态：{}",result.getMsgId(),result.getSendStatus());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }

    /**
     * 批量发送消息
     * @param topic
     * @param tags
     * @param bodies
     */
    public void sendMsg(String topic,String tags,List<String> bodies){

        if(CollectionUtils.isEmpty(bodies)){
            throw new NullPointerException("bodies can't be null");
        }
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);

        try{
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            StopWatch stop = new StopWatch();
            stop.start();

            for(String body:bodies){
                Message message = new Message(topic,tags,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = producer.send(message);
                LOGGER.debug("发送响应MsgId:{}，发送状态：{}",result.getMsgId(),result.getSendStatus());
            }
            stop.stop();
            LOGGER.debug("发送{}条消息耗时：{}",bodies.size(),stop.getTotalTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }

    /**
     * 批量发送消息
     * @param messages
     */
    public void sendMsg(List<Message> messages){

        if(CollectionUtils.isEmpty(messages)){
            throw new NullPointerException("messages can't be null");
        }

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        try{
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();
            for(Message msg:messages){
                SendResult result = producer.send(msg);
                LOGGER.debug("发送响应MsgId:{}，发送状态：{}",result.getMsgId(),result.getSendStatus());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
