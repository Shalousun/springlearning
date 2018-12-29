package com.sunyu.prometheus.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.prometheus.model.ProvinceGatewayCopy1;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/12/29.
 */
public interface ProvinceGatewayCopy1Dao {

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	ProvinceGatewayCopy1 queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<ProvinceGatewayCopy1> queryPage();
}