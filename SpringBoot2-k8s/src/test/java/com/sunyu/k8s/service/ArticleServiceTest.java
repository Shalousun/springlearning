package com.sunyu.k8s.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.k8s.model.Article;
import com.sunyu.k8s.service.ArticleService;

/**
 * @author yu on 2018/12/31.
 */
public class ArticleServiceTest extends ServiceBaseTest {

	@Resource
	private ArticleService articleService;

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