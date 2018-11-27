package com.sunyu.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import com.sunyu.rocketmq.dao.OrderDao;
import com.sunyu.rocketmq.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.starter.annotation.RocketMQTransactionListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yu 2018/11/27.
 */
@Component
@Slf4j
@RocketMQTransactionListener( txProducerGroup ="txTest")
public class OrderTransactionListener implements TransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Resource
    private OrderDao orderDao;

    /**
     * 执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("开始执行本地事务");
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        try{
            Order order = JSON.parseObject(new String(msg.getBody(), "utf-8"),Order.class);
            order.setState(Order.COMPLETED);
            orderDao.update(order);
            return LocalTransactionState.COMMIT_MESSAGE;
        }catch (Exception e){
            e.printStackTrace();
            return LocalTransactionState.UNKNOW;
        }
    }

    /**
     * 事务状态检查，上面是存在Map中的，实际情况可存储到redis
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
