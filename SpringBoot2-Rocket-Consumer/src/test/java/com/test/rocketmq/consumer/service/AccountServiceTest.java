package com.test.rocketmq.consumer.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.test.rocketmq.consumer.model.Account;
import com.test.rocketmq.consumer.service.AccountService;

/**
 * @author yu on 2018/11/27.
 */
public class AccountServiceTest extends ServiceBaseTest {

	@Resource
	private AccountService accountService;

	@Test
	public void testSave() {
		Account entity = new Account();
		CommonResult result = accountService.save(entity);
	}

	@Test
	public void testUpdate() {
		Account entity = new Account();
		CommonResult result = accountService.update(entity);
	}

	@Test
	public void testDelete() {
		CommonResult result = accountService.delete(1);
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