package com.sunyu.redis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.redis.dao.ArticleDaoCache;
import com.sunyu.redis.dao.ArticlesDao;
import com.sunyu.redis.model.Articles;
import com.sunyu.redis.service.ArticlesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yu on 2018/11/09.
 */
@Service("articlesService")
public class ArticlesServiceImpl  implements ArticlesService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ArticlesService.class);

	@Resource
	private ArticleDaoCache articleDaoCache;

	@Override
	public CommonResult save(Articles entity) {
		CommonResult result = new CommonResult();
        try {
        	articleDaoCache.save(entity);
        	result.setSuccess(true);
        } catch (Exception e) {
        	logger.error("ArticlesService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Articles entity) {
		CommonResult result = new CommonResult();
        try {
            articleDaoCache.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ArticlesService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            articleDaoCache.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ArticlesService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Articles entity = articleDaoCache.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("ArticlesService未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        List<Articles> list = articleDaoCache.queryPage(offset,limit);
        return new PageSerializable(list);
    }
}