package com.sunyu.rocketmq.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.power.common.model.CommonResult;

/**
 * @author yu on 2018/11/05.
 */
public class ProvinceServiceTest extends ServiceBaseTest {

	@Resource
	private ProvinceService provinceService;

	@Test
	public void testSave() {
		Province entity = new Province();
		CommonResult result = provinceService.save(entity);
	}

	@Test
	public void testUpdate() {
		Province entity = new Province();
		CommonResult result = provinceService.update(entity);
	}

	@Test
	public void testDelete() {
		CommonResult result = provinceService.delete(1);
	}

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