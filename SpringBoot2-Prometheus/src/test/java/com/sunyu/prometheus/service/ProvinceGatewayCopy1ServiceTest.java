package com.sunyu.prometheus.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.prometheus.model.ProvinceGatewayCopy1;
import com.sunyu.prometheus.service.ProvinceGatewayCopy1Service;

/**
 * @author yu on 2018/12/29.
 */
public class ProvinceGatewayCopy1ServiceTest extends ServiceBaseTest {

	@Resource
	private ProvinceGatewayCopy1Service provinceGatewayCopy1Service;

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