package com.sunyu.k8s.service;


import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.k8s.model.Article;

/**
 *
 * @author yu on 2018/12/31.
 */
public interface ArticleService {

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