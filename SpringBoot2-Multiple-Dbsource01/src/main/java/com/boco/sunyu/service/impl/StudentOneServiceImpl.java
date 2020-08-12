package com.boco.sunyu.service.impl;

import com.boco.sunyu.dao.one.StudentOneDao;
import com.boco.sunyu.dao.two.StudentTwoDao;
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
    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Resource
	private StudentOneDao studentOneDao;

	@Resource
    private StudentTwoDao studentTwoDao;
	

	@Transactional
	@Override
	public CommonResult save(Student entity) {
		CommonResult result = new CommonResult();
        try {
        	studentOneDao.save(entity);
        	studentTwoDao.save(entity);
        	int a = 10/0;
        	result.setSuccess(true);
        } catch (Exception e) {
        	result.setMessage("添加数据失败");
        	logger.error("StudentService添加数据异常：",e);
        	throw new RuntimeException("添加数据失败");
        }
        return result;
	}

	@Override
	public CommonResult update(Student entity) {
		CommonResult result = new CommonResult();
        try {
            studentOneDao.update(entity);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage("修改数据失败");
            logger.error("StudentService修改数据异常：",e);
        }
        return result;
	}

	@Override
	public CommonResult delete(int id) {
		CommonResult result = new CommonResult();
        try {
            studentOneDao.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage("删除数据失败");
            logger.error("StudentService删除数据异常：",e);
        }
        return result;
	}

	@Override
	public CommonResult queryById(int id) {
	    CommonResult result = new CommonResult();
	    Student entity = studentOneDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	result.setData(entity);
        	result.setSuccess(true);
        } else {
        	result.setMessage("没有找到匹配数据");
        	logger.info("StudentService未查询到数据，编号：{}",id);
        }
        return result;
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