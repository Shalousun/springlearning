package com.sunyu.redis.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.redis.model.Province;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/11/08.
 */
public interface ProvinceDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(Province entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<Province> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(Province entity);

	/**
	 * 删除数据
	 * @param id
	 * @return
     */
	int delete(int id);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	Province queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<Province> queryPage();
}