package com.sunyu.apollo.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.apollo.model.Article;
import com.sunyu.apollo.dao.ArticleDao;
import com.sunyu.apollo.service.ArticleService;

/**
 * @author yu on 2018/11/12.
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
	public CommonResult save(Article entity) {
		CommonResult result = new CommonResult();
        try {
        	articleDao.save(entity);
        	result.setSuccess(true);
        } catch (Exception e) {
        	logger.error("ArticleService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Article entity) {
		CommonResult result = new CommonResult();
        try {
            articleDao.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ArticleService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            articleDao.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ArticleService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Article entity = articleDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("ArticleService未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Article> list = articleDao.queryPage();
        return new PageSerializable(list);
    }
}