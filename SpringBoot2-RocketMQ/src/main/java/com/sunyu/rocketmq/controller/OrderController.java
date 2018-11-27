package com.sunyu.rocketmq.controller;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.rocketmq.model.Order;
import com.sunyu.rocketmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/27.
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "createOrder/{userId}")
    public CommonResult createOrder(@PathVariable String userId) throws Exception{
        return orderService.createOrder(userId);
    }

    /**
     * Pagination query order information
     * @param offset page offset
     * @param limit page limit
     * @return
     */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Order> queryPage(@PathVariable int offset, @PathVariable int limit) {
        return orderService.queryPage(offset,limit);
    }
}
