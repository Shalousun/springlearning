package com.sunyu.rocketmq.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.rocketmq.model.Province;
import com.sunyu.rocketmq.dao.ProvinceDao;
import com.sunyu.rocketmq.service.ProvinceService;

/**
 * @author yu on 2018/11/05.
 */
@Service("provinceService")
public class ProvinceServiceImpl  implements ProvinceService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceService.class);

	@Resource
	private ProvinceDao provinceDao;

	@Override
	public CommonResult save(Province entity) {
		CommonResult result = new CommonResult();
        try {
        	provinceDao.save(entity);
        	result.setSuccess(true);
        } catch (Exception e) {
        	logger.error("ProvinceService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Province entity) {
		CommonResult result = new CommonResult();
        try {
            provinceDao.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ProvinceService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            provinceDao.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("ProvinceService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Province entity = provinceDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("ProvinceService未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Province> list = provinceDao.queryPage();
        return new PageSerializable(list);
    }
}