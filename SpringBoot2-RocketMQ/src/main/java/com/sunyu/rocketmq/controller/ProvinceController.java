package com.sunyu.rocketmq.controller;

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
import com.sunyu.rocketmq.model.Province;
import com.sunyu.rocketmq.service.ProvinceService;

/**
 * Province Information Operation Interface
 * @author yu on 2018/11/05.
 */
@RestController
@RequestMapping("province")
public class ProvinceController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceController.class);

	@Resource
	private ProvinceService provinceService;

    /**
	 * Add province info
	 * @param entity province info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(Province entity) {
		return provinceService.save(entity);
	}

    /**
	 * Update province info
	 * @param entity province info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(Province entity) {
		return provinceService.update(entity);
	}

    /**
	 * Delete province by id
	 * @param id province id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return provinceService.delete(id);
	}

    /**
	 * Query province by id
	 * @param id province id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Province> queryById(@PathVariable int id) {
		return provinceService.queryById(id);
	}

    /**
	 * Pagination query province information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Province> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return provinceService.queryPage(offset,limit);
    }
}