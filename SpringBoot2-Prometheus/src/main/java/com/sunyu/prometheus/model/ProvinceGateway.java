package com.sunyu.prometheus.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Table:地区和省份网关对应表
 * @author yu on 2018/12/29.
 */
@Data
@ToString
public class ProvinceGateway implements Serializable {

    private static final long serialVersionUID = -8522004637130168765L;

   	private Long id;

	/** 
	 * 地区名（如广东省等）
	 */
 	private String provinceName;

	/** 
	 * 地区编码（标准，比如：440000 表示广东省）
	 */
 	private String areaCode;

	/** 
	 * 省份端网关地址
	 */
 	private String gatewayUrl;

	/** 
	 * 是否启用
	 */
 	private Integer isEnable;

	/** 
	 * 创建时间
	 */
 	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp gmtCreate;

	/** 
	 * 更新时间
	 */
 	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp gmtUpdate;


}