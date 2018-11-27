package com.sunyu.rocketmq.controller;

import com.alibaba.fastjson.JSON;
import com.power.common.model.CommonResult;
import com.sunyu.rocketmq.listener.OrderTransactionListener;
import com.sunyu.rocketmq.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionMQProducer的demo测试
 * @author yu 2018/11/27.
 */
@Slf4j
@RestController
public class TransactionMsgController {

    @Autowired
    private TransactionMQProducer producer;

    @Autowired
    private OrderTransactionListener orderTransactionListener;

    /**
     *
     * @return
     * @throws Exception
     */
    @GetMapping("testTransaction")
    public CommonResult testTransaction() throws Exception{

        Message message = new Message("t_TopicTest", "Tag1", "12345", "rocketmq测试成功".getBytes());
        producer.setTransactionListener(orderTransactionListener);
        producer.setSendMsgTimeout(10000);
        producer.sendMessageInTransaction(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("传输成功");
                log.info(JSON.toJSONString(sendResult));
            }
            @Override
            public void onException(Throwable e) {
                log.error("传输失败", e);
            }
        });
        return ResultUtil.success("");

    }
}
