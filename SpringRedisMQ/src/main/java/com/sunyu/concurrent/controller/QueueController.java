package com.sunyu.concurrent.controller;

import com.sunyu.concurrent.redis.mq.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/queue")
public class QueueController {

    @Autowired
    SendMessage sendMessage;

    @RequestMapping(value="/redis")
    public void redis(){
        for (int i = 0; i <1000; i++) {
            sendMessage.sendMessage("java",i);
        }
    }
}