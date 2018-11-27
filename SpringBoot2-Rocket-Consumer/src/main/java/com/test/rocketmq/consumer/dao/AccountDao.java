package com.test.rocketmq.consumer.dao;

import java.util.List;
import java.util.Map;

import com.test.rocketmq.consumer.model.Account;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/11/27.
 */
public interface AccountDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(Account entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<Account> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(Account entity);

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
	Account queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<Account> queryPage();
}