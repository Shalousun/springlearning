package com.test.rocketmq.consumer.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Table:
 * @author yu on 2018/11/27.
 */
@Data
@ToString
public class Account implements Serializable {

    private static final long serialVersionUID = -4933579672636580272L;

   	private Integer id;

	/** 
	 * 金额
	 */
 	private Double amount;

	/** 
	 * 用户编号
	 */
 	private String userId;

	/** 
	 * 更新时间
	 */
 	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp updateTime;


}