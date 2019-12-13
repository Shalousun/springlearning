package com.sunyu.kafka.controller;

import com.power.common.model.CommonResult;
import com.sunyu.kafka.model.KafkaMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

/**
 * kafka消息发送测试
 * @author yu 2018/11/9.
 */
@Slf4j
@RestController
public class KafkaController {

    @Value("${kafka.producer.topics}")
    private String producerTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 简单的发送
     * @param name
     * @return
     */
    @PostMapping("/send")
    public CommonResult<String> send(@RequestBody String message) {

        kafkaTemplate.send(producerTopic, message);
        return CommonResult.ok().setResult(message);
    }

    /**
     * 发送key value测试的测试
     * @param kafkaMsg
     * @return
     */
    @PostMapping("/send/keyValue/msg")
    public CommonResult<KafkaMsg> sendKeyValue(@RequestBody KafkaMsg kafkaMsg){
        kafkaTemplate.send(new ProducerRecord<>(kafkaMsg.getTopic(),kafkaMsg.getKey(),kafkaMsg.getValue()));
        return CommonResult.ok().setResult(kafkaMsg);
    }

    /**
     * 同步
     * @param message
     * @return
     */
    @PostMapping("send2")
    public CommonResult send2(@RequestBody String message){
        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send(producerTopic,message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
                log.error("The {} Message send error.",message);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("The send msg {} with off set={}",result,result.getRecordMetadata().offset());
            }
        });
        return CommonResult.ok().setResult(message);
    }

    /**
     * 异步发送消息测试
     * @param message
     * @return
     */
    @Async
    @PostMapping("send3")
    public CommonResult<String> send3(@RequestBody String message){
        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send(producerTopic,message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
                log.error("The {} Message send error.",message);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("The send msg {} with off set={}",result,result.getRecordMetadata().offset());
            }
        });
        return CommonResult.ok().setResult(message);
    }
}
