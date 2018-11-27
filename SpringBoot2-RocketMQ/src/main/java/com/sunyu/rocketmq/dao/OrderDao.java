package com.sunyu.rocketmq.dao;

import com.sunyu.rocketmq.model.Order;

import java.util.List;

/**
 * @author yu 2018/11/27.
 */
public interface OrderDao {

    /**
     * 保存数据
     * @param entity
     * @return
     */
    int save(Order entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    int update(Order entity);

    /**
     * 分页查询数据
     * @return
     */
    List<Order> queryPage();
}
