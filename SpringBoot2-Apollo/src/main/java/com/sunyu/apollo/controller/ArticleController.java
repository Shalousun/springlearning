package com.sunyu.apollo.controller;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.apollo.model.Article;
import com.sunyu.apollo.service.ArticleService;

/**
 * Article Information Operation Interface
 * @author yu on 2018/11/12.
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
	 * Add article info
	 * @param entity article info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(Article entity) {
		return articleService.save(entity);
	}

    /**
	 * Update article info
	 * @param entity article info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(Article entity) {
		return articleService.update(entity);
	}

    /**
	 * Delete article by id
	 * @param id article id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return articleService.delete(id);
	}

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