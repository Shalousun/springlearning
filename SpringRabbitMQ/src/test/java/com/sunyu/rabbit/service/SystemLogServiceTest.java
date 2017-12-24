package com.sunyu.rabbit.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boco.common.model.CommonResult;
import com.sunyu.rabbit.model.SystemLog;
import com.sunyu.rabbit.service.SystemLogService;

/**
 *
 * @author yu
 * @date 2017-04-28 22:50:21
 *
 */
public class SystemLogServiceTest extends ServiceBaseTest{

	@Resource
	private SystemLogService systemLogService;

	@Test
	public void testSave(){
		SystemLog entity = new SystemLog();
		CommonResult result = systemLogService.save(entity);
	}

	@Test
	public void testUpdate(){
		SystemLog entity = new SystemLog();
		CommonResult result = systemLogService.update(entity);
	}

	@Test
	public void testDelete(){
		CommonResult result = systemLogService.delete(1);
	}

	@Test
	public void testQueryById(){

	}

    /**
     * 分页查询
     *
     */
	@Test
	public void testQueryPage(){

	}

}