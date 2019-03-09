package com.sunyu.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import com.sunyu.rocketmq.dao.OrderDao;
import com.sunyu.rocketmq.model.Order;
import lombok.extern.slf4j.Slf4j;



import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
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
public class OrderTransactionListener implements RocketMQLocalTransactionListener {

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
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("开始执行本地事务");
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(transId, status);
        try{
            Order order = (Order) msg.getPayload();
            order.setState(Order.COMPLETED);
            orderDao.update(order);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            e.printStackTrace();
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /**
     * 事务状态检查，上面是存在Map中的，实际情况可存储到redis
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        Integer status = localTrans.get(transId);
        if (null != status) {
            switch (status) {
                case 0:
                    return RocketMQLocalTransactionState.UNKNOWN;
                case 1:
                    return RocketMQLocalTransactionState.COMMIT;
                case 2:
                    return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
