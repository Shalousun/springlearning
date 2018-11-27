package com.test.rocketmq.consumer.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.test.rocketmq.consumer.model.Journal;
import com.test.rocketmq.consumer.service.JournalService;

/**
 * @author yu on 2018/11/27.
 */
public class JournalServiceTest extends ServiceBaseTest {

	@Resource
	private JournalService journalService;

	@Test
	public void testSave() {
		Journal entity = new Journal();
		CommonResult result = journalService.save(entity);
	}

	@Test
	public void testUpdate() {
		Journal entity = new Journal();
		CommonResult result = journalService.update(entity);
	}

	@Test
	public void testDelete() {
		CommonResult result = journalService.delete(1);
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