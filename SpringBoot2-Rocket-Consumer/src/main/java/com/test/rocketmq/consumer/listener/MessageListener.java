package com.test.rocketmq.consumer.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.rocketmq.consumer.model.Account;
import com.test.rocketmq.consumer.model.Journal;
import com.test.rocketmq.consumer.service.AccountService;
import com.test.rocketmq.consumer.service.JournalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.annotation.RocketMQTransactionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 消息消费者
 *
 * @author yu 2018/11/27.
 */
@Slf4j
@Component
public class MessageListener implements MessageListenerConcurrently {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JournalService journalService;

    @Override
    @Transactional
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        MessageExt msg = msgs.get(0);
        try {

            String topic = msg.getTopic();
            //Message Body
            JSONObject messageBody = JSONObject.parseObject(new String(msg.getBody(), "UTF-8"));
            System.out.println(JSON.toJSONString(messageBody));
            String tags = msg.getTags();
            String keys = msg.getKeys();

            String userId = messageBody.getString("userId");
            String orderId = messageBody.getString("orderId");
            Double amount = messageBody.getDouble("amount");

            //打印
            System.out.println(topic + "  " + tags + "    " + keys + "  用户id为： " + userId + ",订单号为： " + orderId + ",消费金额为：" + amount);

            //业务逻辑处理,根据用户id去数据库中 将账户余额更新
            Account account = new Account();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            account.setAmount(amount);
            account.setUserId(userId);
            account.setUpdateTime(timestamp);
            accountService.update(account);

            //保存记录到账户流水表
            Journal journal = new Journal();
            journal.setOrderId(orderId);
            journal.setUserId(userId);
            journal.setAmount(amount);
            journal.setUpdateTime(timestamp);
            journalService.save(journal);

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            //重试测试为三次
            if (msg.getReconsumeTimes() == 3) {

                /**
                 * 如果连续重发3次还是发布出去 证明该订单可能哪里有错
                 * 保存错误日志,然后就不再发送消息了,需要人工介入,逻辑我就不写了
                 */
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            //如果没超过三次,稍后再次发送
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

    }
}
