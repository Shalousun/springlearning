package com.sunyu.redis.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
import lombok.ToString;

/**
 * Table:
 * @author yu on 2018/11/08.
 */
@Data
@ToString
public class Province implements Serializable {

    private static final long serialVersionUID = -5672549364096492005L;

   	private Integer id;

	private String provinceId;

	private String provinceName;

	private Date createTime;


}