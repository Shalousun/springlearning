package com.sunyu.redis.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.sunyu.redis.enums.ErrorCodeEnum;
import com.sunyu.redis.util.ResultUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.redis.model.Province;
import com.sunyu.redis.dao.ProvinceDao;
import com.sunyu.redis.service.ProvinceService;

/**
 * @author yu on 2018/11/08.
 */
@Service("provinceService")
public class ProvinceServiceImpl  implements ProvinceService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceService.class);

	@Resource
	private ProvinceDao provinceDao;

    /**
     * 使用redis 缓存
     * @param entity
     * @return
     */
	@CachePut(value = "province",key = "'province_id_'+#entity.id")
	@Override
	public CommonResult save(Province entity) {
        try {
        	provinceDao.save(entity);
        	return ResultUtil.success();
        } catch (Exception e) {
        	logger.error("ProvinceService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
	}

    @CachePut(value = "province",key = "'province_id_'+#entity.id")
	@Override
	public CommonResult update(Province entity) {
        try {
            provinceDao.update(entity);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("ProvinceService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
	}

	@CacheEvict(value = "province",key = "'province_id_'+#id")
	@Override
	public CommonResult delete(int id) {
        try {
            provinceDao.delete(id);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("ProvinceService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
	}


    @Cacheable(value = "province",key = "'province_id_'+#id")
	@Override
	public CommonResult queryById(int id) {
	    Province entity = provinceDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
            return ResultUtil.success(entity);
        } else {
        	logger.info("ProvinceService未查询到数据，编号：{}",id);
        	return ResultUtil.error(ErrorCodeEnum.NOT_FIND_RESULT);
        }
	}


    @Cacheable(cacheNames = "province.all")
	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Province> list = provinceDao.queryPage();
        return new PageSerializable(list);
    }
}