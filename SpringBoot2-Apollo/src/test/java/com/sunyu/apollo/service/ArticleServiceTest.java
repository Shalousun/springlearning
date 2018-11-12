package com.sunyu.apollo.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.apollo.model.Article;
import com.sunyu.apollo.service.ArticleService;

/**
 * @author yu on 2018/11/12.
 */
public class ArticleServiceTest extends ServiceBaseTest {

	@Resource
	private ArticleService articleService;

	@Test
	public void testSave() {
		Article entity = new Article();
		CommonResult result = articleService.save(entity);
	}

	@Test
	public void testUpdate() {
		Article entity = new Article();
		CommonResult result = articleService.update(entity);
	}

	@Test
	public void testDelete() {
		CommonResult result = articleService.delete(1);
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