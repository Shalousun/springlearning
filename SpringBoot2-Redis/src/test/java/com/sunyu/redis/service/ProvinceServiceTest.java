package com.sunyu.redis.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.redis.model.Province;
import com.sunyu.redis.service.ProvinceService;

/**
 * @author yu on 2018/11/08.
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