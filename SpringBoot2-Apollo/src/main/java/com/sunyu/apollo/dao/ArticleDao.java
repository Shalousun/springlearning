package com.sunyu.apollo.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.apollo.model.Article;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/11/12.
 */
public interface ArticleDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	int save(Article entity);

    /**
     * 批量添加数据
     * @param entityList
     * @return
     */
	int batchSave(List<Article> entityList);

	/**
	 * 更新数据
	 * @param entity
	 * @return
     */
	int update(Article entity);

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
	Article queryById(int id);

	/**
	 * 分页查询数据
	 * @return
     */
	List<Article> queryPage();
}