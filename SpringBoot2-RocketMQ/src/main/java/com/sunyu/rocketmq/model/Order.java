package com.sunyu.rocketmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yu 2018/11/27.
 */
@Data
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = -344119726948138266L;

    public static final int PREPARED = 1;

    public static final int COMPLETED = 2;

    /**
     * 订单自增编号
     */
    private long id;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 金额
     */
    private Double amount;

    private Integer state;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;


}
