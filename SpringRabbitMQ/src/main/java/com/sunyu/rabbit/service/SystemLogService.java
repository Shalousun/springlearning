package com.sunyu.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.boco.common.model.CommonResult;
import com.sunyu.rabbit.model.SystemLog;

/**
 *
 * @author yu
 * @date 2017-04-28 22:50:20
 *
 */

public interface SystemLogService{

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	CommonResult save(SystemLog entity);

	/**
	 * 修改数据
	 * @param entity
	 * @return
     */
	CommonResult update(SystemLog entity);

	/**
	 * 删除数据
	 * @param id
	 * @return
     */
	CommonResult delete(int id);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	CommonResult queryById(int id);

	/**
     * 分页查询
     * @param offset 偏移量
     * @param limit 每页大小
     * @return
     */
    PageInfo queryPage(int offset, int limit);
}