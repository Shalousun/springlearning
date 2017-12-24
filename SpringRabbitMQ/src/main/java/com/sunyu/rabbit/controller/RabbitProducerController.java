package com.sunyu.rabbit.controller;

import com.boco.common.model.CommonResult;
import com.sunyu.rabbit.provider.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yu on 2017/4/29.
 */
@RestController
public class RabbitProducerController {


    @Autowired
    private Producer producer;
    @Value("#{appConfig['mq.queue']}")
    private String queueId;


    @GetMapping("/send")
    public CommonResult sendMsg(){
        CommonResult result = new CommonResult();
        Map<String, Object> map = new HashMap<>();
        map.put("data", "hello rabbitmq");
        try {
            producer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
            result.setMessage("通过rabbitmq发送消息成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage("发送消息异常");
            e.printStackTrace();
        }
        return result;
    }

}
