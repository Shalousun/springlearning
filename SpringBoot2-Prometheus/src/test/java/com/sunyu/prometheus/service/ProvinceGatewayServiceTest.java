package com.sunyu.prometheus.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.prometheus.model.ProvinceGateway;
import com.sunyu.prometheus.service.ProvinceGatewayService;

/**
 * @author yu on 2018/12/29.
 */
public class ProvinceGatewayServiceTest extends ServiceBaseTest {

	@Resource
	private ProvinceGatewayService provinceGatewayService;

	@Test
	public void testQueryById() {

	}

    /**
     * 分页查询
     *
     */
	@Test
	public void testQueryPage() {

	}

}