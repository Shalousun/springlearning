package com.sunyu.rabbit.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.boco.common.model.CommonResult;
import com.sunyu.rabbit.model.SystemLog;
import com.sunyu.rabbit.dao.SystemLogDao;
import com.sunyu.rabbit.service.SystemLogService;

/**
 *
 * @author yu
 * @date 2017-04-28 22:50:21
 *
 */
@Service("systemLogService")
public class SystemLogServiceImpl  implements SystemLogService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SystemLogService.class);

	@Resource
	private SystemLogDao systemLogDao;

	@Override
	public CommonResult save(SystemLog entity){
		CommonResult result = new CommonResult();
        try{
        	systemLogDao.save(entity);
        	result.setSuccess(true);
        }catch(Exception e){
        	result.setMessage("添加数据失败");
        	logger.error("添加数据异常：",e);
        }
        return result;
	}

	@Override
	public CommonResult update(SystemLog entity){
		CommonResult result = new CommonResult();
        try{
            systemLogDao.update(entity);
            result.setSuccess(true);
        }catch(Exception e){
            result.setMessage("修改数据失败");
            logger.error("修改数据异常：",e);
        }
        return result;
	}

	@Override
	public CommonResult delete(int id){
		CommonResult result = new CommonResult();
        try{
            systemLogDao.delete(id);
            result.setSuccess(true);
        }catch(Exception e){
            result.setMessage("删除数据失败");
            logger.error("删除数据异常：",e);
        }
        return result;
	}



	@Override
	public CommonResult queryById(int id){
	    CommonResult result = new CommonResult();
	    SystemLog entity = systemLogDao.queryById(id);
	    if(null != entity){
        	result.setData(entity);//成功返回数据
        	result.setSuccess(true);
        }else{
        	result.setMessage("没有找到匹配数据");
        	logger.info("未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageInfo queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<SystemLog> list = systemLogDao.queryPage();
        return new PageInfo(list);
    }

}