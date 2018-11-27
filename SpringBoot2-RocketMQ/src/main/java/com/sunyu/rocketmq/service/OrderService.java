package com.sunyu.rocketmq.service;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;

/**
 * @author yu 2018/11/27.
 */
public interface OrderService {

    /**
     *
     * @param userId
     * @return
     */
    CommonResult createOrder(String userId) throws Exception;

    /**
     * 分页查询
     * @param offset 偏移量
     * @param limit 每页大小
     * @return
     */
    PageSerializable queryPage(int offset, int limit);
}
