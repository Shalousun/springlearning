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
import com.sunyu.prometheus.model.ProvinceGateway;
import com.sunyu.prometheus.service.ProvinceGatewayService;

/**
 * ProvinceGateway Information Operation Interface
 * @author yu on 2018/12/29.
 */
@RestController
@RequestMapping("provinceGateway")
public class ProvinceGatewayController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceGatewayController.class);

	@Resource
	private ProvinceGatewayService provinceGatewayService;

    /**
	 * Query provinceGateway by id
	 * @param id provinceGateway id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<ProvinceGateway> queryById(@PathVariable int id) {
		return provinceGatewayService.queryById(id);
	}

    /**
	 * Pagination query provinceGateway information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<ProvinceGateway> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return provinceGatewayService.queryPage(offset,limit);
    }
}