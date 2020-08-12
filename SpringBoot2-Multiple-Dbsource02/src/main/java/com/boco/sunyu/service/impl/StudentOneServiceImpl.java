package com.boco.sunyu.service.impl;


import com.boco.sunyu.dao.StudentOneDao;
import com.boco.sunyu.dao.StudentTwoDao;
import com.boco.sunyu.model.Student;
import com.boco.sunyu.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.model.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  22:01:41.
 */
@Service("studentOneService")
public class StudentOneServiceImpl implements StudentService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private StudentOneDao studentOneDao;

	@Resource
    private StudentTwoDao studentTwoDao;
	

	@Transactional
	@Override
	public CommonResult save(Student entity) {
        try {
        	studentOneDao.save(entity);
        	studentTwoDao.save(entity);
        	return CommonResult.ok();
        } catch (Exception e) {
        	logger.error("StudentService添加数据异常：",e);
        	throw new RuntimeException("添加数据失败");
        }
	}

	@Override
	public CommonResult update(Student entity) {
        try {
            studentOneDao.update(entity);
            return CommonResult.ok();
        } catch (Exception e) {
            logger.error("StudentService修改数据异常：",e);
            throw new RuntimeException("修改数据失败");
        }
	}

	@Override
	public CommonResult delete(int id) {
        try {
            studentOneDao.delete(id);
            return CommonResult.ok();
        } catch (Exception e) {
            logger.error("StudentService删除数据异常：",e);
            throw new RuntimeException("删除数据失败");
        }
	}

	@Override
	public CommonResult<Student> queryById(int id) {
	    Student entity = studentOneDao.queryById(id);
	    if (null != entity) {
	      return CommonResult.ok().setResult(entity);
        } else {
        	logger.info("StudentService未查询到数据，编号：{}",id);
            throw new RuntimeException("没有找到匹配数据");
        }
	}

	@Override
    public PageInfo queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset,limit);
        List<Student> list = studentOneDao.queryPage();
        return new PageInfo(list);
    }

    @Override
    public List<Map<String,Object>> queryToListMap(Map<String,Object> params){
        return studentOneDao.queryToListMap(params);
    }
}