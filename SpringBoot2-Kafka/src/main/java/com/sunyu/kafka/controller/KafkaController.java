package com.sunyu.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/9.
 */
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 简单的发送
     * @param name
     * @return
     */
    @GetMapping("/send")
    public String send(String name) {
        kafkaTemplate.send("mytopic", name);
        return name;
    }

    /**
     * 同步
     * @param message
     * @return
     */
    @GetMapping("send2")
    public String send2(String message){
        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send("mytopic",message);
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
        return message;
    }

    @Async
    @GetMapping("send3")
    public String send3(String message){
        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send("mytopic",message);
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
        return message;
    }
}
