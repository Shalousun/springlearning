package com.sunyu.rocket.controller;

import com.sunyu.rocket.producer.RocketMQProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProducerController {

    @Resource
    private RocketMQProducer rocketMQProducer;

    @GetMapping(value = "send")
    public boolean testSendMsg(){
        //和rocketmq.properties的一样方便测试
        String topic = "TopicTest";
        String tag = "push";

        rocketMQProducer.sendMsg(topic,tag,"Hello RocketMQ");
        return true;
    }
}
