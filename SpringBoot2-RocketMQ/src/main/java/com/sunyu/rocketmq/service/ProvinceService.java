package com.sunyu.rocketmq.service;


import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.rocketmq.model.Province;

/**
 *
 * @author yu on 2018/11/05.
 */
public interface ProvinceService {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	CommonResult save(Province entity);

	/**
	 * 修改数据
	 * @param entity
	 * @return
     */
	CommonResult update(Province entity);

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
    PageSerializable queryPage(int offset, int limit);
}