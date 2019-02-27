package com.sunyu.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.power.common.util.UUIDUtil;
import com.sunyu.rocketmq.dao.OrderDao;
import com.sunyu.rocketmq.model.Order;
import com.sunyu.rocketmq.service.OrderService;
import com.sunyu.rocketmq.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.remoting.common.RemotingHelper;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yu 2018/11/27.
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Transactional
    @Override
    public CommonResult createOrder(String userId) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setAmount(666D);
        order.setState(Order.PREPARED);
        order.setUserName("test");
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        String uuid = UUIDUtil.getUuid();
        order.setOrderId(uuid);
        orderDao.save(order);
        //发送消息
        sendMessage(order);
        log.info("订单：{} 入库成功,发送消息成功",uuid);
        return ResultUtil.success("");
    }

    @Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Order> list = orderDao.queryPage();
        return new PageSerializable(list);
    }

    private void sendMessage(Order order) throws Exception {

        Message msg = MessageBuilder.withPayload(JSON.toJSONString(order).getBytes(RemotingHelper.DEFAULT_CHARSET)).build();
        rocketMQTemplate.sendMessageInTransaction("test", "test-topic", msg, null);
    }
}
