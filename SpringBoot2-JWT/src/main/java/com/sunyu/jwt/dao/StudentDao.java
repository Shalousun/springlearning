package com.sunyu.jwt.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.jwt.model.Student;

/**
 * Created by ApplicationPower.
 * @author yu on 2019/04/21.
 */
public interface StudentDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(Student entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<Student> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(Student entity);

	/**
	 * 删除数据
	 * @param id
	 * @return
     */
	int delete(Long id);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	Student queryById(Long id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<Student> queryPage();
}