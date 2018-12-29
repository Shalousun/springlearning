package com.sunyu.prometheus.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.prometheus.model.ProvinceGateway;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/12/29.
 */
public interface ProvinceGatewayDao {

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	ProvinceGateway queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<ProvinceGateway> queryPage();
}