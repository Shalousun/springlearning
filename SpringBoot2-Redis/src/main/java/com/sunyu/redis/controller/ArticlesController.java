package com.sunyu.redis.controller;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.redis.model.Articles;
import com.sunyu.redis.service.ArticlesService;

/**
 * Articles Information Operation Interface
 * @author yu on 2018/11/09.
 */
@RestController
@RequestMapping("articles")
public class ArticlesController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(ArticlesController.class);

	@Resource
	private ArticlesService articlesService;

    /**
	 * Add articles info
	 * @param entity articles info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(@RequestBody Articles entity) {
		return articlesService.save(entity);
	}

    /**
	 * Update articles info
	 * @param entity articles info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(@RequestBody Articles entity) {
		return articlesService.update(entity);
	}

    /**
	 * Delete articles by id
	 * @param id articles id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return articlesService.delete(id);
	}

    /**
	 * Query articles by id
	 * @param id articles id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Articles> queryById(@PathVariable int id) {
		return articlesService.queryById(id);
	}

    /**
	 * Pagination query articles information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Articles> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return articlesService.queryPage(offset,limit);
    }
}