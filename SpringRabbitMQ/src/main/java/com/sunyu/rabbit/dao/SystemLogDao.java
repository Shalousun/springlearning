package com.sunyu.rabbit.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.rabbit.model.SystemLog;

/**
 *
 * @author yu
 * @date 2017-04-28 22:50:20
 *
 *
 */

public interface SystemLogDao{

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(SystemLog entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<SystemLog> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(SystemLog entity);

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
	SystemLog queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<SystemLog> queryPage();
}