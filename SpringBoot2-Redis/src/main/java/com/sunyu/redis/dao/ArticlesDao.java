package com.sunyu.redis.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.redis.model.Articles;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/11/09.
 */
public interface ArticlesDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(Articles entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<Articles> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(Articles entity);

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
	Articles queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<Articles> queryPage();
}