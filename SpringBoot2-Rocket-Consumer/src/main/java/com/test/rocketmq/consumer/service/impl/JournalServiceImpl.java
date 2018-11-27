package com.test.rocketmq.consumer.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.test.rocketmq.consumer.model.Journal;
import com.test.rocketmq.consumer.dao.JournalDao;
import com.test.rocketmq.consumer.service.JournalService;

/**
 * @author yu on 2018/11/27.
 */
@Service("journalService")
public class JournalServiceImpl  implements JournalService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(JournalService.class);

	@Resource
	private JournalDao journalDao;

	@Override
	public CommonResult save(Journal entity) {
		CommonResult result = new CommonResult();
        try {
        	journalDao.save(entity);
        	result.setSuccess(true);
        } catch (Exception e) {
        	logger.error("JournalService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Journal entity) {
		CommonResult result = new CommonResult();
        try {
            journalDao.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("JournalService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            journalDao.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("JournalService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Journal entity = journalDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("JournalService未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Journal> list = journalDao.queryPage();
        return new PageSerializable(list);
    }
}