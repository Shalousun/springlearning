package com.test.rocketmq.consumer.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.test.rocketmq.consumer.model.Account;
import com.test.rocketmq.consumer.dao.AccountDao;
import com.test.rocketmq.consumer.service.AccountService;

/**
 * @author yu on 2018/11/27.
 */
@Service("accountService")
public class AccountServiceImpl  implements AccountService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Resource
	private AccountDao accountDao;

	@Override
	public CommonResult save(Account entity) {
		CommonResult result = new CommonResult();
        try {
        	accountDao.save(entity);
        	result.setSuccess(true);
        } catch (Exception e) {
        	logger.error("AccountService添加数据异常：",e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Account entity) {
		CommonResult result = new CommonResult();
        try {
            accountDao.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("AccountService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            accountDao.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("AccountService删除数据异常：",e);
            throw new RuntimeException("删除数据异常");
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Account entity = accountDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("AccountService未查询到数据，编号：{}",id);
        }
        return result;
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Account> list = accountDao.queryPage();
        return new PageSerializable(list);
    }
}