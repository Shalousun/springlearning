package com.sunyu.k8s.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.k8s.enums.ErrorCode;
import com.sunyu.k8s.model.Article;
import com.sunyu.k8s.dao.ArticleDao;
import com.sunyu.k8s.service.ArticleService;

/**
 * @author yu on 2018/12/31.
 */
@Service("articleService")
public class ArticleServiceImpl  implements ArticleService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ArticleService.class);

	@Resource
	private ArticleDao articleDao;

	@Override
	public CommonResult queryById(int id) {
	    Article entity = articleDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	return CommonResult.ok(ErrorCode.SUCCESS).setResult(entity);
        } else {
        	logger.info("ArticleService未查询到数据，编号：{}", id);
        	return CommonResult.fail(ErrorCode.UNKNOWN_ERROR);
        }
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<Article> list = articleDao.queryPage();
        return new PageSerializable(list);
    }
}