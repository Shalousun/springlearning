package com.sunyu.curator.service;


import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.curator.model.Student;

/**
 *
 * @author yu on 2019/07/08.
 */
public interface StudentService {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	CommonResult save(Student entity);

	/**
	 * 修改数据
	 * @param entity
	 * @return
     */
	CommonResult update(Student entity);

	/**
	 * 删除数据
	 * @param id
	 * @return
     */
	CommonResult delete(Long id);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	CommonResult queryById(Long id);

	/**
     * 分页查询
     * @param offset 偏移量
     * @param limit 每页大小
     * @return
     */
    PageSerializable queryPage(int offset, int limit);
}