package com.sunyu.k8s.dao;

import java.util.List;
import java.util.Map;

import com.sunyu.k8s.model.Article;

/**
 * Created by ApplicationPower.
 * @author yu on 2018/12/31.
 */
public interface ArticleDao {

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