package com.sunyu.k8s.controller;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.k8s.model.Article;
import com.sunyu.k8s.service.ArticleService;

/**
 * Article Information Operation Interface
 * @author yu on 2018/12/31.
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Resource
	private ArticleService articleService;

    /**
	 * Query article by id
	 * @param id article id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Article> queryById(@PathVariable int id) {
		return articleService.queryById(id);
	}

    /**
	 * Pagination query article information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Article> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return articleService.queryPage(offset,limit);
    }
}