package com.boco.sunyu.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.boco.common.model.CommonResult;
import com.boco.sunyu.model.Student;

/**
 *
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  22:01:41.
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

    /**
     * query result to list of map
     * @param params Map查询参数
     * @return
     */
    List<Map<String,Object>> queryToListMap(Map<String, Object> params);
}