package com.sunyu.rocketmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yu 2018/11/6.
 */
@Data
@ToString
public class OrderPaidEvent implements Serializable {


    private static final long serialVersionUID = -7672552332007257668L;

    private String orderId;

    private BigDecimal paidMoney;



}
