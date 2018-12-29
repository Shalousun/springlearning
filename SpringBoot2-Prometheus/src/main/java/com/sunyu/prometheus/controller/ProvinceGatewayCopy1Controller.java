package com.sunyu.prometheus.controller;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.prometheus.model.ProvinceGatewayCopy1;
import com.sunyu.prometheus.service.ProvinceGatewayCopy1Service;

/**
 * ProvinceGatewayCopy1 Information Operation Interface
 * @author yu on 2018/12/29.
 */
@RestController
@RequestMapping("provinceGatewayCopy1")
public class ProvinceGatewayCopy1Controller {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceGatewayCopy1Controller.class);

	@Resource
	private ProvinceGatewayCopy1Service provinceGatewayCopy1Service;

    /**
	 * Query provinceGatewayCopy1 by id
	 * @param id provinceGatewayCopy1 id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<ProvinceGatewayCopy1> queryById(@PathVariable int id) {
		return provinceGatewayCopy1Service.queryById(id);
	}

    /**
	 * Pagination query provinceGatewayCopy1 information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<ProvinceGatewayCopy1> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return provinceGatewayCopy1Service.queryPage(offset,limit);
    }
}